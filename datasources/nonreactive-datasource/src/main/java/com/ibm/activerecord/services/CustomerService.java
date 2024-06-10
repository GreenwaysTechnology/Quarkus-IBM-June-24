package com.ibm.activerecord.services;

import com.ibm.activerecord.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    //apis
    public List<Customer> findAll() {
        return Customer.listAll();
    }

    public Customer findById(Integer id) {
        return Customer.findById(id);
    }

    @Transactional
    public void create(Customer customer) {
        customer.persist();
    }

    @Transactional
    public Customer update(Integer id, Customer customer) {
        if (customer.name == null) {
            throw new WebApplicationException("Customer Name was not found" + 422);
        }
        Customer customerEntity = Customer.findById(id);
        if (customerEntity == null) {
            throw new WebApplicationException("Customer with ID of " + id + "does not exits");
        }
        customerEntity.name = customer.name;
        customerEntity.city = customer.city;
        return customerEntity;
    }

    @Transactional
    public Response delete(Long id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            throw new WebApplicationException("Customer with ID of " + id + "does not exits");

        }
        customer.delete();
        return Response.status(204).build();
    }

}
