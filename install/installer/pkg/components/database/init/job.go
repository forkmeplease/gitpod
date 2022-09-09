// Copyright (c) 2021 Gitpod GmbH. All rights reserved.
// Licensed under the GNU Affero General Public License (AGPL).
// See License-AGPL.txt in the project root for license information.

// This runs the init scripts in a non-inCluster DB instance

package init

import (
	"fmt"

	"github.com/gitpod-io/gitpod/installer/pkg/cluster"

	"github.com/gitpod-io/gitpod/installer/pkg/common"
	batchv1 "k8s.io/api/batch/v1"
	corev1 "k8s.io/api/core/v1"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/apimachinery/pkg/runtime"
	"k8s.io/utils/pointer"
)

func job(ctx *common.RenderContext) ([]runtime.Object, error) {
	if disableMigration := common.IsDatabaseMigrationDisabled(ctx); disableMigration {
		return nil, nil
	}

	objectMeta := metav1.ObjectMeta{
		Name:        fmt.Sprintf("%s-session", Component),
		Namespace:   ctx.Namespace,
		Labels:      common.CustomizeLabel(ctx, Component, common.TypeMetaBatchJob),
		Annotations: common.CustomizeAnnotation(ctx, Component, common.TypeMetaBatchJob),
	}

	volumes := []corev1.Volume{{
		Name: sqlInitScripts,
		VolumeSource: corev1.VolumeSource{ConfigMap: &corev1.ConfigMapVolumeSource{
			LocalObjectReference: corev1.LocalObjectReference{Name: sqlInitScripts},
		}},
	}}
	volumeMounts := []corev1.VolumeMount{{
		Name:      sqlInitScripts,
		MountPath: "/db-init-scripts",
		ReadOnly:  true,
	}}

	// dbenv already contains DB_CUSTOM_CA with the value we want, put for the mysql CLI we need to project that value into a file
	sslOptions := ""
	if ctx.Config.Database.SSL != nil && ctx.Config.Database.SSL.CustomCA != "" {
		volumes = append(volumes, corev1.Volume{
			Name: customCaMountName,
			VolumeSource: corev1.VolumeSource{Secret: &corev1.SecretVolumeSource{
				SecretName: common.DBCustomCaSecretName,
			}},
		})
		volumeMounts = append(volumeMounts, corev1.VolumeMount{
			Name:      customCaMountName,
			MountPath: customCaPath,
			ReadOnly:  true,
		})
		sslOptions = fmt.Sprintf(" --ssl-mode=VERIFY_IDENTITY --ssl-ca=%s ", customCaPath)
	}

	return []runtime.Object{&batchv1.Job{
		TypeMeta:   common.TypeMetaBatchJob,
		ObjectMeta: objectMeta,
		Spec: batchv1.JobSpec{
			TTLSecondsAfterFinished: pointer.Int32(60),
			Template: corev1.PodTemplateSpec{
				ObjectMeta: objectMeta,
				Spec: corev1.PodSpec{
					Affinity:           common.NodeAffinity(cluster.AffinityLabelMeta),
					RestartPolicy:      corev1.RestartPolicyNever,
					ServiceAccountName: Component,
					EnableServiceLinks: pointer.Bool(false),
					Volumes:            volumes,
					// The init container is designed to emulate Helm hooks
					InitContainers: []corev1.Container{*common.DatabaseWaiterContainer(ctx)},
					Containers: []corev1.Container{{
						Name:            fmt.Sprintf("%s-session", Component),
						Image:           ctx.ImageName(common.ThirdPartyContainerRepo(ctx.Config.Repository, ""), dbSessionsImage, dbSessionsTag),
						ImagePullPolicy: corev1.PullIfNotPresent,
						Env: common.MergeEnv(
							common.DatabaseEnv(&ctx.Config),
						),
						Command: []string{
							"sh",
							"-c",
							fmt.Sprintf("mysql -h $DB_HOST --port $DB_PORT -u $DB_USERNAME -p$DB_PASSWORD %s< /db-init-scripts/init.sql", sslOptions),
						},
						VolumeMounts: volumeMounts,
					}},
				},
			},
		},
	}}, nil
}
