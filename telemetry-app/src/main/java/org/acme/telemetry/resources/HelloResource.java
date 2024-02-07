package org.acme.telemetry.resources;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
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

  private LongCounter helloCounter = GlobalOpenTelemetry.get().getMeter(HelloResource.class.getName()).counterBuilder("hello-counter").build();

  @GET
  @Path("hello/{name}")
  public String hello(@PathParam("name") String name) {
    service.sayHello(name);
    helloCounter.add(1, Attributes.of(AttributeKey.stringKey("name"), name));
    return name;
  }

}
