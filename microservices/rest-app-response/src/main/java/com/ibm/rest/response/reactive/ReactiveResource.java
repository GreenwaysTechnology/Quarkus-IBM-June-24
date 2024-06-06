package com.ibm.rest.response.reactive;

import com.ibm.rest.response.enity.Customer;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("reactive")
public class ReactiveResource {

    @GET
    public Uni<List<Customer>> getCustomers() {
        List<Customer> customers = List.of(
                new Customer(1, "Subramaian", "Coimbatore"),
                new Customer(2, "Murugan", "Coimbatore"),
                new Customer(3, "Karthik", "Chennai")
        );
        return Uni.createFrom().item(customers);
    }

    @GET
    @Path("response")
    public Uni<Response> getCustomerResponse() {
        List<Customer> customers = List.of(
                new Customer(1, "Subramaian", "Coimbatore"),
                new Customer(2, "Murugan", "Coimbatore"),
                new Customer(3, "Karthik", "Chennai")
        );
        return Uni.createFrom()
                .item(customers)
                .onItem()
                .transform(customers1 -> Response.ok(customers1).header("message", "customers"))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}
