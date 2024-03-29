package org.acme;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloConsumer {

  @Inject
  HelloService helloService;

  @Incoming("hello")
  @WithSpan
  public void consume(String name) throws InterruptedException {
    helloService.sayHello(name);
  }

}
