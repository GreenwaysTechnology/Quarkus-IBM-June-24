package com.ibm.activerecord.resources;

import com.ibm.activerecord.entity.Customer;
import com.ibm.activerecord.services.CustomerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GET
    @Path("{id}")
    public Customer findById(@PathParam("id") Integer id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new WebApplicationException("Customer with Id of " + id + " does not exit");
        }
        return customer;
    }

    @POST
    public Response create(Customer customer) {
        if (customer.id != null) {
            throw new WebApplicationException("Id was invalidately set on Request", 422);
        }
        customerService.create(customer);
        return Response.ok(customer).status(201).build();
    }

    @PUT
    @Path("{id}")
    public Customer update(@PathParam("id") Integer id, Customer customer) {
        Customer customer1 = customerService.update(id, customer);
        return customer1;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        return customerService.delete(id);
    }


}
