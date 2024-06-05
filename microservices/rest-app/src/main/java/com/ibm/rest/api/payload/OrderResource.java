package com.ibm.rest.api.payload;

import com.ibm.rest.api.payload.entity.Order;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("orders")
public class OrderResource {

    @POST
    public String save(Order order) {
        System.out.println(order);
        return "Order saved";
    }

}
