package org.acme.telemetry.resources;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloService {
  private static final Logger LOG = Logger.getLogger(HelloService.class);

  @WithSpan
  public void sayHello(@SpanAttribute String name) {
    LOG.info(String.format("I said hello to %s", name));
  }
}
