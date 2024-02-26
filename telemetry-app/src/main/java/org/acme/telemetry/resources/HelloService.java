package org.acme.telemetry.resources;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.LongStream;
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

  Random random = new Random();

  @WithSpan
  public void sayHello(@SpanAttribute String name) {
    long r = randomNumberService.get();

    LOG.info(String.format("I said hello to %s", name));
    LOG.info(r);
    helloEmitter.send(name);
  }
}
