# README

## Installation

```bash
# Build docker images
cd telemetry-app && quarkus image build docker && cd ..
cd random-generator && quarkus image build docker && cd ..
cd consumer && quarkus image build docker && cd ..

# Setup minikube
minikube start --driver=kvm2 --cni=flannel --cpus=4 --memory=8000
docker image save -o telemetry-app.tar stmichael/telemetry-app:1.5.0-SNAPSHOT
minikube image load telemetry-app.tar
rm telemetry-app.tar
docker image save -o random-generator.tar stmichael/random-generator:1.0.0-SNAPSHOT
minikube image load random-generator.tar
rm random-generator.tar
docker image save -o consumer.tar stmichael/consumer:1.0.0-SNAPSHOT
minikube image load consumer.tar
rm consumer.tar

# Install Otel operator
kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.14.4/cert-manager.yaml
kubectl apply -f https://github.com/open-telemetry/opentelemetry-operator/releases/latest/download/opentelemetry-operator.yaml

# Install Strimzi
helm install strimzi-cluster-operator oci://quay.io/strimzi-helm/strimzi-kafka-operator --values kafka/values.yml

# Deploy the stuff
kubectl create namespace monitoring
kubectl apply -f k8s/loki
kubectl apply -f k8s/tempo
kubectl apply -f k8s/prometheus
kubectl apply -f k8s/pyroscope
kubectl apply -f k8s/grafana
kubectl apply -f k8s/alloy-otlp
kubectl apply -f k8s/alloy-profiling
kubectl apply -f k8s/alloy-kafka
kubectl apply -f k8s/cluster-otel-collector
kubectl apply -f k8s/kafka/kafka.yml
kubectl apply -f k8s/random-generator
kubectl apply -f k8s/consumer
kubectl apply -f k8s/consumer2
kubectl apply -f k8s/telemetry-app
```

Deploying pixie is a bit tricky and doesn't work out of the box yet. First you have to create an account on https://work.withpixie.ai/ and create an API key.
```
# Install pixie CLI
bash -c "$(curl -fsSL https://withpixie.ai/install.sh)"
# Authenticate with the API key
px auth login --manual
# Deploy the pixie stuff (make sure the current context is minikube beforehand!)
px deploy
```
This will take some time. Pixie sends it's collected data to https://work.withpixie.ai/ where you can run queries against the data.

The pixie datasource in grafana doesn't work yet. Please create a new dataource for your purposes.
