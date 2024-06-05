package com.ibm.rest.api;

import jakarta.ws.rs.*;

@Path("customers")
public class CustomerResource {

    //api
    @GET
    public String findAll() {
        return "findAll";
    }

    @GET
    @Path("comments")
    public String findComments(){
        return  "Customer Comments";
    }

    @POST
    public String save() {
        return "save";
    }

    @PUT
    public String update() {
        return "Update";
    }

    @DELETE
    public String remove() {
        return "Remove";
    }
}
