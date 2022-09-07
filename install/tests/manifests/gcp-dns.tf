variable "gcp_zone" { default = "tests-gitpod-self-hosted-com" }
variable "dns_sa_creds" { default = null }

module "add-dns-record" {
  source = "github.com/gitpod-io/gitpod//install/infra/modules/tools/cloud-dns-ns?ref=main"
  credentials      = var.dns_sa_creds
  nameservers      = module.${cluster}.name_servers
  dns_project      = "dns-for-playgrounds"
  managed_dns_zone = var.gcp_zone
  domain_name      = var.domain_name
}
