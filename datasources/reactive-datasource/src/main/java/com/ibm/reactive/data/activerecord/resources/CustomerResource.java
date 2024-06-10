package com.ibm.reactive.data.activerecord.resources;

import com.ibm.reactive.data.activerecord.Customer;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("customers")
public class CustomerResource {

    @GET
    public Uni<List<Customer>> findAll() {
        return Customer.listAll();
    }

    @Path("{id}")
    @GET
    public Uni<Response> findById(@PathParam("id") Long id) {
        return Customer.findById(id).onItem().transform(entity -> {
            if (entity == null) {
                throw new WebApplicationException("Entity not Found");
            }
            return Response.ok(entity).build();
        });
    }


}
