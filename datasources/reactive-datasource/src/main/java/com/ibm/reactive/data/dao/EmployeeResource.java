package com.ibm.reactive.data.dao;

import com.ibm.reactive.data.activerecord.Customer;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("employees")
public class EmployeeResource {
    @Inject
    EmployeeRepository employeeRepository;

    @GET
    public Uni<List<Employee>> findAll() {
        return employeeRepository.listAll();
    }

    @Path("{id}")
    @GET
    public Uni<Response> findById(@PathParam("id") Long id) {
        return employeeRepository.findById(id).onItem().transform(entity -> {
            if (entity == null) {
                throw new WebApplicationException("Employee with ID Of " + id + "does not exits", 404);
            }
            return Response.ok(entity).status(200).build();
        });
    }

    @POST
    @WithTransaction
    public Uni<Response> create(Employee employee) {
        if (employee == null || employee.name == null) {
            throw new WebApplicationException("Customer Not Found", 400);
        }
        return employeeRepository.persist(employee)
                .onItem().transform(entity -> Response.ok()
                        .status(201).entity(entity).build());
    }

    @PUT
    @Path("{id}")
    @WithTransaction
    public Uni<Response> update(@PathParam("id") Long id, Employee employee) {
        if (employee == null || employee.name == null) {
            throw new WebApplicationException("Customer Not Found", 400);
        }
        String query = "city='" + employee.getCity() + "' WHERE id=?1";
        System.out.println(query);
        return employeeRepository.update(query, id)
                .onItem()
                .transform(entity -> Response.ok().status(200).entity(entity).build());
    }

    @DELETE
    @Path("{id}")
    @WithTransaction
    public Uni<Response> delete(@PathParam("id") Long id) {
        return employeeRepository.deleteById(id)
                .onItem()
                .transform(isDeleted -> isDeleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build());
    }

}
