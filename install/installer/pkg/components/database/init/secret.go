// Copyright (c) 2022 Gitpod GmbH. All rights reserved.
// Licensed under the GNU Affero General Public License (AGPL).
// See License-AGPL.txt in the project root for license information.

package init

import (
	"github.com/gitpod-io/gitpod/installer/pkg/common"
	corev1 "k8s.io/api/core/v1"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/apimachinery/pkg/runtime"
)

func secrets(ctx *common.RenderContext) ([]runtime.Object, error) {
	if ctx.Config.Database.SSL == nil || ctx.Config.Database.SSL.CustomCA == "" {
		return nil, nil
	}

	return []runtime.Object{&corev1.Secret{
		TypeMeta: common.TypeMetaSecret,
		ObjectMeta: metav1.ObjectMeta{
			Name:      common.DBCustomCaSecretName,
			Namespace: ctx.Namespace,
			Labels:    common.DefaultLabels(Component),
		},
		Data: map[string][]byte{
			common.DBCustomCaFileName: []byte(ctx.Config.Database.SSL.CustomCA),
		},
	}}, nil
}
