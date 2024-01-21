Install helm repos
```
helm repo add grafana https://grafana.github.io/helm-charts
helm repo add open-telemetry https://open-telemetry.github.io/opentelemetry-helm-charts
```

Install otel collector for apps to collect metrics and traces
```
helm -n monitoring install otel-collector open-telemetry/opentelemetry-collector -f otel-collector/app-custom.yml
```

Install Grafana Tempo to collect traces
```
helm -n monitoring install tempo grafana/tempo-distributed -f tempo/custom.yml
```
