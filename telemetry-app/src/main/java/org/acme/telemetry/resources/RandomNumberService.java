package org.acme.telemetry.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/random")
@RegisterRestClient(configKey = "random-generator")
public interface RandomNumberService {
  @GET
  long get();
}
