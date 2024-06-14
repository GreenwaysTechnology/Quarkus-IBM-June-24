package org.acme;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello")
public class GreetingResource {

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @CacheResult(cacheName = "hello")
    public String hello() {
        logger.info("hello is called");
        return "Hello from RESTEasy Reactive";
    }
    @DELETE
    @CacheInvalidate(cacheName ="hello")
    public void invalidCache(){

    }
}
