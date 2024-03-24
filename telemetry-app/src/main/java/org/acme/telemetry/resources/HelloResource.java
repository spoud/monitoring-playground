package org.acme.telemetry.resources;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/")
@Produces("text/plain")
public class HelloResource {

  @Inject
  private HelloService service;

  @Inject
  private MeterRegistry meterRegistry;

  @GET
  @Path("hello/{name}")
  public String hello(@PathParam("name") String name) throws InterruptedException {
    service.sayHello(name);
    Counter.builder("hello-counter").tag("name", name).register(meterRegistry).increment();
    return String.format("Hello %s", name);
  }

}
