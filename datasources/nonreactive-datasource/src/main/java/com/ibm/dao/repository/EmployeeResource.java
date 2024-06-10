package com.ibm.dao.repository;

import com.ibm.dao.Employee;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("employees")
public class EmployeeResource {
    @Inject
    EmployeeRepository employeeRepository;

    @GET
    public List<Employee> findAll() {
        return employeeRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Employee findById(@PathParam("id") Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new WebApplicationException("Employee With ID Of " + id + "does not exit");
        }
        return employee;
    }

    @POST
    @Transactional
    public Response create(Employee employee) {
        if (employee.id != null) {
            throw new WebApplicationException("Employee Creation Error");
        }
        employeeRepository.persist(employee);
        return Response.ok(employee).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    //update
    public Employee update(@PathParam("id") Long id, Employee employee) {
        if (employee.name == null || employee.city == null) {
            throw new WebApplicationException("Employee Name or city was not set on Request");
        }
        Employee employeeEntity = employeeRepository.findById(id);

        if (employeeEntity == null) {
            throw new WebApplicationException("Employee Id was not found");
        }
        employeeEntity.name = employee.name;
        employeeEntity.city = employee.city;
        return employeeEntity;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Employee employeeEntity = employeeRepository.findById(id);
        if (employeeEntity == null) {
            throw new WebApplicationException("Employee not available");
        }
        employeeRepository.delete(employeeEntity);
        return Response.status(204).build();
    }
}
