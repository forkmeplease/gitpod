#!/bin/bash

# shellcheck disable=SC2034

set -euo pipefail

SCRIPT_PATH=$(realpath "$(dirname "$0")")

# shellcheck source=../lib/common.sh
source "$(realpath "${SCRIPT_PATH}/../lib/common.sh")"

# terraform function
import "terraform.sh"

PROJECT_ROOT=$(realpath "${SCRIPT_PATH}/../../")

if [ -z "${WERFT_HOST-}" ]; then
  export TF_INPUT=0
fi

WORKSPACE="${TF_VAR_preview_name:-}"
TARGET_DIR="${PROJECT_ROOT}/preview/infrastructure/harvester"
# Setting the TF_DATA_DIR is advisable if we set the PLAN_LOCATION in a different place than the dir with the tf
TF_DATA_DIR="${TARGET_DIR}"

# Illustration purposes, but this will set the plan location to $TARGET_DIR/harvester.plan if PLAN_LOCATION is not set
static_plan="$(realpath "${TARGET_DIR}")/$(basename "${TARGET_DIR}").plan"
PLAN_LOCATION="${PLAN_LOCATION:-$static_plan}"

# export all variables
shopt -os allexport

terraform_init

PLAN_EXIT_CODE=0
terraform_plan || PLAN_EXIT_CODE=$?

# If there are changes
if [[ ${PLAN_EXIT_CODE} = 2 ]]; then
  # If we're NOT in werft, ask if we want to apply the plan
  if [ -z "${WERFT_HOST-}" ]; then
    ask "Do you want to apply the plan?"
  fi
  terraform_apply
fi

if [ -n "${DESTROY-}" ] && [ -n "${WORKSPACE}" ]; then
  delete_workspace "${WORKSPACE}"
fi
