terraform {

  backend "gcs" {
    bucket = "3f4745df-preview-tf-state"
    prefix = "preview"
  }

  required_version = ">= 1.2"
  required_providers {
    harvester = {
      source  = "harvester/harvester"
      version = ">=0.5.1"
    }
    k8s = {
      source  = "hashicorp/kubernetes"
      version = ">= 2.0"
    }
  }
}

# https://registry.terraform.io/providers/harvester/harvester/latest/docs
provider "harvester" {
  alias      = "harvester"
  kubeconfig = file(var.harvester_kube_path)
}

# https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs
provider "k8s" {
  alias       = "dev"
  config_path = var.dev_kube_path
}

# https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs
provider "k8s" {
  alias       = "harvester"
  config_path = var.harvester_kube_path
}