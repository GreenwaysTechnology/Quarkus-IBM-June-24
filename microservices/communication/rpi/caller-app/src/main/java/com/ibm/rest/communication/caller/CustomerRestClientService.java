package com.ibm.rest.communication.caller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//@RegisterRestClient
@RegisterRestClient(configKey = "customers-api")
@Path("customers")
public interface CustomerRestClientService {
    //api declaration of callee
    @GET
    String findAll();


    @GET
    @Path("reactive")
    Uni<String> findByReactive();

    @GET
    @Path("{id}")
    String findById(@PathParam("id") Long id);

    @POST
    String create(String payload);

    @PUT
    @Path("{id}")
    String update(@PathParam("id") Long id);

    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Long id);
}
