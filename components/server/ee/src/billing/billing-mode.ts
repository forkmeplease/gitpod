/**
 * Copyright (c) 2022 Gitpod GmbH. All rights reserved.
 * Licensed under the GNU Affero General Public License (AGPL).
 * See License-AGPL.txt in the project root for license information.
 */

import { inject, injectable } from "inversify";

import { Team, User } from "@gitpod/gitpod-protocol";
import { ConfigCatClientFactory } from "@gitpod/gitpod-protocol/lib/experiments/configcat-server";
import { SubscriptionService } from "@gitpod/gitpod-payment-endpoint/lib/accounting";
import { Subscription } from "@gitpod/gitpod-protocol/lib/accounting-protocol";
import { Config } from "../../../src/config";
import { StripeService } from "../user/stripe-service";
import { BillingMode } from "@gitpod/gitpod-protocol/lib/billing-mode";
import { TeamDB, TeamSubscription2DB, TeamSubscriptionDB, UserDB } from "@gitpod/gitpod-db/lib";
import { Plans } from "@gitpod/gitpod-protocol/lib/plans";
import { AttributionId } from "@gitpod/gitpod-protocol/lib/attribution";
import { TeamSubscription, TeamSubscription2 } from "@gitpod/gitpod-protocol/lib/team-subscription-protocol";

export const BillingModes = Symbol("BillingModes");
export interface BillingModes {
    getBillingMode(attributionId: AttributionId, now: Date): Promise<BillingMode>;
    getBillingModeForUser(user: User, now: Date): Promise<BillingMode>;
    getBillingModeForTeam(team: Team, now: Date): Promise<BillingMode>;
}

/**
 *
 * Some rules for how we decide about BillingMode someone is in:
 *  - Teams: Do they have either:
 *    - Chargebee subscription              => cb
 *    - UBB (& maybe Stripe subscription):  => ubb
 *  - Users: Do they have either:
 *    - personal Chargebee subscription     => cb
 *    - personal Stripe Subscription        => ubb
 *    - at least one Stripe Team seat       => ubb
 */
@injectable()
export class BillingModesImpl implements BillingModes {
    @inject(Config) protected readonly config: Config;
    @inject(ConfigCatClientFactory) protected readonly configCatClientFactory: ConfigCatClientFactory;
    @inject(SubscriptionService) protected readonly subscriptionSvc: SubscriptionService;
    @inject(StripeService) protected readonly stripeSvc: StripeService;
    @inject(TeamSubscriptionDB) protected readonly teamSubscriptionDb: TeamSubscriptionDB;
    @inject(TeamSubscription2DB) protected readonly teamSubscription2Db: TeamSubscription2DB;
    @inject(TeamDB) protected readonly teamDB: TeamDB;
    @inject(UserDB) protected readonly userDB: UserDB;

    public async getBillingMode(attributionId: AttributionId, now: Date): Promise<BillingMode> {
        switch (attributionId.kind) {
            case "team":
                const team = await this.teamDB.findTeamById(attributionId.teamId);
                if (!team) {
                    throw new Error(`Cannot find team with id '${attributionId.teamId}'!`);
                }
                return this.getBillingModeForTeam(team, now);
            case "user":
                const user = await this.userDB.findUserById(attributionId.userId);
                if (!user) {
                    throw new Error(`Cannot find user with id '${attributionId.userId}'!`);
                }
                return this.getBillingModeForUser(user, now);
        }
    }

    protected async isBillingModeEnabled(subject: { user?: User; team?: Team }): Promise<boolean> {
        // This is a double safety-net to smoothen and de-risk the rollout of BillingMode, because it not only affects
        // new behavior which is in focus and subject to test anyway (usage-based), but also old behavior (chargebee).
        const teams = subject.user ? await this.teamDB.findTeamsByUser(subject.user.id) : undefined;
        return await this.configCatClientFactory().getValueAsync("isBillingModeEnabled", false, {
            user: subject.user,
            teams,
            teamId: subject.team?.id,
            teamName: subject.team?.name,
        });
    }

    async getBillingModeForUser(user: User, now: Date): Promise<BillingMode> {
        if (!this.config.enablePayment) {
            // Payment is not enabled. E.g. Self-Hosted.
            return { mode: "none" };
        }
        if (!(await this.isBillingModeEnabled({ user }))) {
            return { mode: "chargebee" };
        }

        // Is Usage Based Billing enabled for this user or not?
        const teams = await this.teamDB.findTeamsByUser(user.id);
        const isUsageBasedBillingEnabled = await this.configCatClientFactory().getValueAsync(
            "isUsageBasedBillingEnabled",
            false,
            {
                user,
                teams,
            },
        );

        // 1. UBB enabled?
        if (!isUsageBasedBillingEnabled) {
            // UBB is not enabled: definitely chargebee
            return { mode: "chargebee" };
        }

        // 2. Any personal subscriptions?
        // Chargebee takes precedence
        function isPersonalSubscription(s: Subscription): boolean {
            return !Plans.getById(s.planId)?.team;
        }
        function isOldTeamSubscription(s: Subscription): boolean {
            return !!Plans.getById(s.planId)?.team && !s.teamMembershipId;
        }
        const cbSubscriptions = await this.subscriptionSvc.getActivePaidSubscription(user.id, now);
        const cbTeamSubscriptions = cbSubscriptions.filter((s) => isOldTeamSubscription(s));
        const cbPersonalSubscriptions = cbSubscriptions.filter(
            (s) => isPersonalSubscription(s) && s.planId !== Plans.FREE_OPEN_SOURCE.chargebeeId,
        );
        let canUpgradeToUBB = false;
        if (cbPersonalSubscriptions.length > 0) {
            if (cbPersonalSubscriptions.every((s) => Subscription.isCancelled(s, now.toISOString()))) {
                // The user has one or more paid subscriptions, but all of them have already been cancelled
                canUpgradeToUBB = true;
            } else {
                // The user has at least one paid personal subscription
                return {
                    mode: "chargebee",
                };
            }
        }

        // Stripe: Active personal subsciption?
        let hasUbbPersonal = false;
        const customer = await this.stripeSvc.findCustomerByUserId(user.id);
        if (customer) {
            const subscription = await this.stripeSvc.findUncancelledSubscriptionByCustomer(customer.id);
            if (subscription) {
                hasUbbPersonal = true;
            }
        }

        // 3. Check team memberships/plans
        // UBB overrides wins if there is _any_. But if there is none, use the existing Chargebee subscription.
        const teamsModes = await Promise.all(teams.map((t) => this.getBillingModeForTeam(t, now)));
        const hasUbbPaidTeam = teamsModes.some((tm) => tm.mode === "usage-based" && !!tm.paid);
        const hasCbTeam = teamsModes.some((tm) => tm.mode === "chargebee");
        const hasCbTeamSeat = cbTeamSubscriptions.length > 0;

        if (hasUbbPaidTeam || hasUbbPersonal) {
            // UBB is greedy: once a user has at least a paid team membership, they should benefit from it!
            const result: BillingMode = { mode: "usage-based" };
            if (hasCbTeam) {
                result.hasChargebeeTeamPlan = true;
            }
            if (hasCbTeamSeat) {
                result.hasChargebeeTeamSubscription = true;
            }
            return result;
        }
        if (hasCbTeam || hasCbTeamSeat || canUpgradeToUBB) {
            const result: BillingMode = { mode: "chargebee", canUpgradeToUBB: true }; // UBB is enabled, but no seat nor subscription yet.

            // TODO(gpl): Q: How to test the free-tier, then? A: Make sure you have no CB seats anymore
            // For that we lists all Team Subscriptions/Team Memberships that are "blocking" you, and display them in the UI somewhere.
            const teamNames = [];
            for (const tm of teamsModes) {
                if (tm.mode === "chargebee" && tm.teamNames) {
                    teamNames.push(`Team Membership: ${tm.teamNames}`);
                }
            }
            const tsOwners = await Promise.all(cbTeamSubscriptions.map((s) => this.mapTeamSubscriptionToOwnerName(s)));
            for (const owner of tsOwners) {
                if (!owner) {
                    continue;
                }
                const [ts, ownerName] = owner;
                teamNames.push(`Team Subscription '${Plans.getById(ts.planId)?.name}' (owner: ${ownerName})`);
            }
            if (teamNames.length > 0) {
                result.teamNames = teamNames;
            }

            return result;
        }

        // UBB free tier
        return { mode: "usage-based" };
    }

    protected async mapTeamSubscriptionToOwnerName(s: Subscription): Promise<[TeamSubscription, string] | undefined> {
        if (!s || !s.teamSubscriptionSlotId) {
            return undefined;
        }
        const ts = await this.teamSubscriptionDb.findTeamSubscriptionBySlotId(s.teamSubscriptionSlotId!);
        if (!ts) {
            return undefined;
        }
        const user = await this.userDB.findUserById(ts.userId);
        if (!user) {
            return undefined;
        }
        return [ts, user.name || user.fullName || "---"];
    }

    async getBillingModeForTeam(team: Team, _now: Date): Promise<BillingMode> {
        if (!this.config.enablePayment) {
            // Payment is not enabled. E.g. Self-Hosted.
            return { mode: "none" };
        }
        if (!(await this.isBillingModeEnabled({ team }))) {
            return { mode: "chargebee" };
        }
        const now = _now.toISOString();

        // Is Usage Based Billing enabled for this team?
        const isUsageBasedBillingEnabled = await this.configCatClientFactory().getValueAsync(
            "isUsageBasedBillingEnabled",
            false,
            {
                teamId: team.id,
                teamName: team.name,
            },
        );

        // 1. UBB enabled?
        if (!isUsageBasedBillingEnabled) {
            return { mode: "chargebee" };
        }

        // 2. Any Chargbee TeamSubscription2 (old Team Subscriptions are not relevant here, as they are not associated with a team)
        const teamSubscription = await this.teamSubscription2Db.findForTeam(team.id, now);
        if (teamSubscription && TeamSubscription2.isActive(teamSubscription, now)) {
            const result: BillingMode = { mode: "chargebee", teamNames: [team.name] };
            if (TeamSubscription2.isCancelled(teamSubscription, now)) {
                // The team has a paid subscription, but it's already cancelled: can upgrade to UBB!
                result.canUpgradeToUBB = true;
            }

            return result;
        }

        // 3. Now we're usage-based. We only have to figure out whether we have a plan yet or not.
        const result: BillingMode = { mode: "usage-based" };
        const customer = await this.stripeSvc.findCustomerByTeamId(team.id);
        if (customer) {
            const subscription = await this.stripeSvc.findUncancelledSubscriptionByCustomer(customer.id);
            if (subscription) {
                result.paid = true;
            }
        }
        return result;
    }
}
