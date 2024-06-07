package com.ibm.cdi.services.interfaces;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("repo")
public class RepositoryResource {

    @Inject
    @Named("nosql")
    Repository repository;

    @GET
    public String findAll() {
        return repository.findAll();
    }
}
