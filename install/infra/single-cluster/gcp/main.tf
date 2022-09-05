terraform {
  backend "gcs" {
    bucket = "nightly-tests"
    prefix = "tf-state/84430-azure.tfstate"
  }

  required_providers {
    google = {
      source = "hashicorp/google"
    }

    kubernetes = {
      source = "hashicorp/kubernetes"
    }

    helm = {
      source = "hashicorp/helm"
    }
  }
}
