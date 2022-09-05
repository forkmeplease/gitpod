# the cluster_name should be of length less than 15 characters and surrounded by double quotes
cluster_name = "gp-84430-azure"

# a cloudDNS zone and certificate request will be created for this domain; surround the domain name within double quotes
domain_name = "84430-azure.tests.gitpod-self-hosted.com"

region      = "europe-west1"
zone        = "europe-west1-d"
project     = "sh-automated-tests"
credentials = "/workspace/gcp.json"

cluster_version = "1.22"

enable_external_database = true
enable_external_storage  = true
enable_external_registry = true
