package org.acme.telemetry.resources;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloService {
  private static final Logger LOG = Logger.getLogger(HelloService.class);

  @Inject
  @Channel("hello")
  Emitter<String> helloEmitter;

  @RestClient
  RandomNumberService randomNumberService;

  @WithSpan
  public void sayHello(@SpanAttribute String name) throws InterruptedException {
    long r = randomNumberService.get();

    LOG.info(String.format("I said hello to %s", name));
    var what = Math.floorMod(r, 5);
    switch (what) {
      case 0:
        throw new RuntimeException(String.format("%s messed up", name));
      case 1:
        Thread.sleep(5000);
        break;
      default:
        helloEmitter.send(name);
        break;
    }
  }
}
