#!/bin/bash

set -euo pipefail

SCRIPT_PATH=$(realpath "$(dirname "$0")")

# shellcheck source=../lib/common.sh
source "$(realpath "${SCRIPT_PATH}/../lib/common.sh")"

import "terraform.sh"

if [ -z "${WORKSPACE-}" ]; then
  log_error "Must provide WORKSPACE"
  exit "${ERROR_NO_WORKSPACE}"
fi

set_workspace "${WORKSPACE}"
