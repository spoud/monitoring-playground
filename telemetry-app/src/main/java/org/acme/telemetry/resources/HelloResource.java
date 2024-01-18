package org.acme.telemetry.resources;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/")
@Produces("text/plain")
public class HelloResource {

  private final MeterRegistry meterRegistry;

  public HelloResource(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  @GET
  @Path("hello/{name}")
  public String hello(@PathParam("name") String name) {
    meterRegistry.counter("acme.hello", "type", name).increment();
    return name;
  }

}
