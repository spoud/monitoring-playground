package org.acme;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HelloService {

  private static final Logger LOG = Logger.getLogger(HelloService.class);

  private final Random random = new Random();

  @WithSpan
  public void sayHello(String name) throws InterruptedException {
    LOG.info(String.format("%s said hello", name));
    Thread.sleep(random.nextInt(3));
  }

}
