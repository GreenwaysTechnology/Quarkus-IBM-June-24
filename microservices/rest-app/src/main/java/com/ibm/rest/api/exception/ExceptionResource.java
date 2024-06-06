package com.ibm.rest.api.exception;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;

@Path("exception")
public class ExceptionResource {

    @Path("{name}")
    @GET
    public String getName(@PathParam("name") String name) {
        if (name.equals("admin")) {
            return "Hello " + name;
        }
        throw new WebApplicationException("Name is not Matching");
    }
}
