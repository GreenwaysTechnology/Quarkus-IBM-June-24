package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message", defaultValue = "Welcome to Quarkus")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message;
    }

    @GET
    @Path("name")
    public String getName() {
        return ConfigProvider.getConfig().getOptionalValue("app.user.name", String.class).orElse("Default Name");
    }
}
