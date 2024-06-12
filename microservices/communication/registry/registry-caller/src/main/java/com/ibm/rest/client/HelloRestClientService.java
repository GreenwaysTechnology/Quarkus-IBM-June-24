package com.ibm.rest.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("hello")
@RegisterRestClient(configKey = "hello-api")
public interface HelloRestClientService {
    @GET
    Uni<String> hello();
}
