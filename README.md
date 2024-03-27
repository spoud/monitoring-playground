# README

## Installation

```bash
# Build docker images
export QUARKUS_CONTAINER_IMAGE_GROUP=docker.collaud.me
cd telemetry-app && quarkus image build docker && cd ..
cd random-generator && quarkus image build docker && cd ..
docker build -t docker.collaud.me/kafka:latest k8s/kafka

docker push docker.collaud.me/telemetry-app:1.5.0-SNAPSHOT
docker push docker.collaud.me/random-generator:1.0.0-SNAPSHOT
docker push docker.collaud.me/kafka:latest

# Deploy the stuff
kubectl create namespace monitoring
kubectl apply -f k8s/loki
kubectl apply -f k8s/tempo
kubectl apply -f k8s/prometheus
kubectl apply -f k8s/pyroscope
kubectl apply -f k8s/grafana
kubectl apply -f k8s/otel-collector
kubectl apply -f k8s/kafka
kubectl apply -f k8s/random-generator
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
