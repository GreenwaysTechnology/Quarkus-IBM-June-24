package com.ibm.reactive.data.activerecord.resources;

import com.ibm.reactive.data.activerecord.Customer;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
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

    @POST
    public Uni<Response> create(Customer customer) {
        //error handler
        if (customer == null && customer.name == null) {
            throw new WebApplicationException("Customer Not Found");
        }
        //In ActiveRecord pattern with Reactive, there is no Transaction annotation rather we have
        //write code
        return Panache.withTransaction(customer::persist)
                .replaceWith(Response.ok(customer).status(201).build());
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@PathParam("id") Long id, Customer customer) {
        //error handler
        if (customer == null && customer.name == null) {
            throw new WebApplicationException("Customer Not Found");
        }
        //In ActiveRecord pattern with Reactive, there is no Transaction annotation rather we have
        //write code
        return Panache.withTransaction(() -> {
            return Customer.<Customer>findById(id)
                    .onItem()
                    .ifNotNull()
                    .invoke(entity -> {
                        //update logic
                        entity.name = customer.name;
                        entity.city = customer.city;
                    })
                    .onItem()
                    .ifNotNull()
                    .transform(entity -> Response.ok(entity).status(200).build())
                    .onItem()
                    .ifNull().continueWith(Response.ok().status(Response.Status.NOT_FOUND)::build);
        });


    }

    //delete
    @Path("{id}")
    @DELETE
    public Uni<Response> remove(@PathParam("id") Long id) {
        return Panache.withTransaction(() -> {
            return Customer.deleteById(id).map(isDeleted ->
                    isDeleted ? Response.ok().status(Response.Status.NO_CONTENT).build() :
                            Response.ok().status(Response.Status.NOT_FOUND).build()
            );
        });
    }


}
