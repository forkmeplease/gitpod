// Copyright (c) 2025 Gitpod GmbH. All rights reserved.
// Licensed under the GNU Affero General Public License (AGPL).
// See License.AGPL.txt in the project root for license information.

// Code generated by connect-kotlin. DO NOT EDIT.
//
// Source: gitpod/experimental/v1/editor_service.proto
//
package io.gitpod.publicapi.experimental.v1

import com.connectrpc.Headers
import com.connectrpc.ResponseMessage

public interface EditorServiceClientInterface {
  public suspend fun listEditorOptions(request: EditorServiceOuterClass.ListEditorOptionsRequest,
      headers: Headers = emptyMap()):
      ResponseMessage<EditorServiceOuterClass.ListEditorOptionsResponse>
}
