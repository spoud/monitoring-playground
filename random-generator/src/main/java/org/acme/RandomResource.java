package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/random")
public class RandomResource {
    private static final Logger LOG = Logger.getLogger(RandomResource.class);

    @Inject
    RandomService randomService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public long get() {
        LOG.info("Getting new random number");
        return randomService.get();
    }
}
