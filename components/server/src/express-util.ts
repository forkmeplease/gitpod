/**
 * Copyright (c) 2020 Gitpod GmbH. All rights reserved.
 * Licensed under the GNU Affero General Public License (AGPL).
 * See License.AGPL.txt in the project root for license information.
 */

import { URL } from "url";
import express from "express";
import * as crypto from "crypto";
import { IncomingHttpHeaders } from "http";
import { GitpodHostUrl } from "@gitpod/gitpod-protocol/lib/util/gitpod-host-url";
import { ensureUrlHasFragment } from "./auth/fragment-utils";
import { log } from "@gitpod/gitpod-protocol/lib/util/logging";
import { TrustedValue } from "@gitpod/gitpod-protocol/lib/util/scrubbing";

export const query = (...tuples: [string, string][]) => {
    if (tuples.length === 0) {
        return "";
    }
    return "?" + tuples.map((t) => `${t[0]}=${encodeURIComponent(t[1])}`).join("&");
};

// Strict: We only allow connections from the base domain, so disallow connections from all other Origins
//      Only (current) exception: If no Origin header is set, skip the check!
// Non-Strict: "rely" on subdomain parsing (do we still need this?)
export const isAllowedWebsocketDomain = (originHeader: string, gitpodHostName: string): boolean => {
    if (!originHeader) {
        // Origin header check not applied because of empty Origin header
        return true;
    }

    try {
        const originUrl = new URL(originHeader);
        const originHostname = originUrl.hostname;
        return originHostname === gitpodHostName;
    } catch (err) {
        return false;
    }
};

/**
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-Forwarded-For
 *
 * X-Forwarded-For: <client>, <proxy1>, <proxy2>
 *
 * @returns fingerprint which is a hash over (potential) client ip (or just proxy ip) and User Agent
 */
export function getRequestingClientInfo(req: express.Request) {
    const ip = clientIp(req);
    const ua = req.get("user-agent");
    const fingerprint = crypto.createHash("sha256").update(`${ip}–${ua}`).digest("hex");
    return { ua, fingerprint };
}

/**
 * Catches exceptions from an async handler and puts them back into the express handler chain.
 * Assumes handlers take care of regular forwarding themselves.
 *
 * @param handler
 * @returns
 */
export function asyncHandler(
    handler: (req: express.Request, res: express.Response, next: express.NextFunction) => Promise<void>,
): express.Handler {
    return (req: express.Request, res: express.Response, next: express.NextFunction) => {
        handler(req, res, next).catch((err) => next(err));
    };
}

/**
 * Turns all unhandled requests into an error
 * @param req
 * @param res
 * @param next
 * @returns
 */
export function unhandledToError(req: express.Request, res: express.Response, next: express.NextFunction) {
    if (isAnsweredRequest(req, res)) {
        return next();
    }
    /* Handle unknown routes gracefully to improve user experience and security.
     * - Use a 404 status to indicate a "Not Found" error.
     * - Provide a clear and informative message to guide the user.
     * - Avoid exposing stack traces to prevent potential security vulnerabilities.
     * Note: Detailed error logging is delegated to the `bottomErrorHandler()` function.
     */
    res.status(404).send(
        "Resource Not Accessible: The content you're attempting to access may have been removed, renamed, or is temporarily unavailable. Kindly verify the URL and retry.",
    );
}

/**
 * Logs all errors, and responds unanswered requests.
 * @param log
 */
export function bottomErrorHandler(log: (...args: any[]) => void): express.ErrorRequestHandler {
    return (err: any, req: express.Request, response: express.Response, next: express.NextFunction) => {
        if (!err) {
            return next();
        }

        let msg = "undefined";
        let status = 500;
        if (err instanceof Error) {
            msg = err.toString() + "\nStack: " + err.stack;
            status = typeof (err as any).status === "number" ? (err as any).status : 500;
        } else {
            msg = err.toString();
        }
        log(err, {
            originalUrl: req.originalUrl,
            headers: req.headers,
            cookies: req.cookies,
        });
        if (!isAnsweredRequest(req, response)) {
            response.status(status).send({ error: msg });
        }
    };
}

export function isAnsweredRequest(req: express.Request, res: express.Response) {
    return res.headersSent || req.originalUrl.endsWith(".websocket");
}

export const takeFirst = (h: string | string[] | undefined): string | undefined => {
    if (Array.isArray(h)) {
        if (h.length < 1) {
            return undefined;
        }
        return h[0];
    }
    return h;
};

export function clientIp(req: express.Request): string | undefined {
    const clientIp = takeFirst(req.headers["x-real-ip"]);
    if (!clientIp) {
        return undefined;
    }
    return clientIp.split(",")[0];
}

export function toHeaders(headers: IncomingHttpHeaders): Headers {
    const result = new Headers();
    for (const [key, value] of Object.entries(headers)) {
        result.set(key, value as string);
    }
    return result;
}

export interface ClientHeaderFields {
    ip?: string;
    userAgent?: string;
    dnt?: string;
    clientRegion?: string;
}

export function toClientHeaderFields(expressReq: express.Request): ClientHeaderFields {
    return {
        ip: clientIp(expressReq),
        userAgent: expressReq.headers["user-agent"],
        dnt: takeFirst(expressReq.headers.dnt),
        clientRegion: takeFirst(expressReq.headers["x-glb-client-region"]),
    };
}

/**
 * Common validation logic for returnTo URLs.
 * @param returnTo The URL to validate
 * @param hostUrl The host URL configuration
 * @param allowedPatterns Array of regex patterns that are allowed for the pathname
 * @returns true if the URL is valid, false otherwise
 */
function validateReturnToUrlWithPatterns(
    returnTo: string,
    allowedBaseUrl: GitpodHostUrl,
    allowedPatterns?: RegExp[],
): boolean {
    try {
        const url = new URL(returnTo);
        const baseUrl = allowedBaseUrl.url;

        // Must be same origin OR www.gitpod.io exception
        const isSameOrigin = url.origin === baseUrl.origin;
        const isGitpodWebsite = url.protocol === "https:" && url.hostname === "www.gitpod.io";

        if (!isSameOrigin && !isGitpodWebsite) {
            return false;
        }

        // For www.gitpod.io, only allow root path
        if (isGitpodWebsite) {
            return url.pathname === "/";
        }

        if (allowedPatterns !== undefined) {
            // Check if pathname matches any allowed pattern
            const isAllowedPath = allowedPatterns.some((pattern) => pattern.test(url.pathname));
            if (!isAllowedPath) {
                return false;
            }
        }

        // For complete-auth, require ONLY message parameter (used by OAuth flows)
        if (url.pathname === "/complete-auth") {
            const searchParams = new URLSearchParams(url.search);
            const paramKeys = Array.from(searchParams.keys());
            return paramKeys.length === 1 && paramKeys[0] === "message" && searchParams.has("message");
        }

        return true;
    } catch (error) {
        // Invalid URL
        return false;
    }
}

/**
 * Validates returnTo URLs for login API endpoints.
 * Login API allows broader navigation after authentication.
 */
export function validateLoginReturnToUrl(returnTo: string, hostUrl: GitpodHostUrl): boolean {
    // We just verify the domain
    return validateReturnToUrlWithPatterns(returnTo, hostUrl, undefined);
}

/**
 * Validates returnTo URLs for authorize API endpoints.
 * Authorize API allows complete-auth callbacks and dashboard pages for scope elevation.
 */
export function validateAuthorizeReturnToUrl(returnTo: string, hostUrl: GitpodHostUrl): boolean {
    const allowedPatterns = [
        // 1. complete-auth callback for OAuth popup windows
        /^\/complete-auth$/,

        // 2. Dashboard pages (for scope elevation flows)
        /^\/$/, // Root
        /^\/new\/?$/, // Create workspace page
        /^\/quickstart\/?$/, // Quickstart page
    ];

    return validateReturnToUrlWithPatterns(returnTo, hostUrl, allowedPatterns);
}

export function getSafeReturnToParam(req: express.Request, validator?: (url: string) => boolean): string | undefined {
    // @ts-ignore Type 'ParsedQs' is not assignable
    const returnToURL: string | undefined = req.query.redirect || req.query.returnTo;
    if (!returnToURL) {
        return;
    }

    if (validator && !validator(returnToURL)) {
        log.debug("The redirect URL does not match allowed patterns", { query: new TrustedValue(req.query).value });
        return;
    }

    return returnToURL;
}

/**
 * Safe redirect wrapper that automatically ensures URLs have fragments to prevent
 * OAuth token inheritance attacks.
 *
 * When OAuth providers redirect with tokens in URL fragments, browsers inherit
 * fragments from the current page if the target URL doesn't have one. This wrapper
 * automatically applies fragment protection to all redirects.
 *
 * @param res Express response object
 * @param url URL to redirect to
 * @param status Optional HTTP status code (default: 302)
 */
export function safeFragmentRedirect(res: express.Response, url: string, status?: number): void {
    const protectedUrl = ensureUrlHasFragment(url);
    if (status) {
        res.redirect(status, protectedUrl);
    } else {
        res.redirect(protectedUrl);
    }
}
