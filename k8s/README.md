helm repo add grafana https://grafana.github.io/helm-charts
helm -n monitoring install tempo grafana/tempo-distributed -f tempo/custom.yml
