package com.ibm.rest.response.collections;

import com.ibm.rest.response.enity.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("orders")
public class OrderResource {

    @GET
    public Order getOrder() {
        return new Order(1, "CREATED", 344.44, "This is fruits");
    }

    @GET
    @Path("list")
    public List<Order> getOrders() {
        return List.of(
                new Order(1, "CREATED", 344.44, "This is fruits"),
                new Order(2, "PENDING", 44.00, "This is fruits"),
                new Order(3, "DISPATCHED", 33434.89, "This is fruits")
                );
    }


}
