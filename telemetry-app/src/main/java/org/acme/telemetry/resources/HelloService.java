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
import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloService {
  private static final Logger LOG = Logger.getLogger(HelloService.class);

  @Inject
  @Channel("hello")
  Emitter<String> helloEmitter;

  Random random = new Random();

  @WithSpan
  public void sayHello(@SpanAttribute String name) {
    // simulate computationally expensive operation
    Arrays.sort(LongStream.rangeClosed(0, 10000000).map((x) -> random.nextLong()).toArray());

    LOG.info(String.format("I said hello to %s", name));
    helloEmitter.send(name);
  }
}
