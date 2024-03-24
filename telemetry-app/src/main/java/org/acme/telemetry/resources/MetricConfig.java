package org.acme.telemetry.resources;

import io.micrometer.core.instrument.Metrics;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.instrumentation.micrometer.v1_5.OpenTelemetryMeterRegistry;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetricConfig {
  @Startup
  void registerOtelMeterRegistry() {
    var meterRegistry = OpenTelemetryMeterRegistry.builder(GlobalOpenTelemetry.get()).build();
    Metrics.addRegistry(meterRegistry);
  }
}
