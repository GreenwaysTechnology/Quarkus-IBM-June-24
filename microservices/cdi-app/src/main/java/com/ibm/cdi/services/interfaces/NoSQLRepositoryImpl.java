package com.ibm.cdi.services.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("nosql")
public class NoSQLRepositoryImpl implements Repository {
    @Override
    public String findAll() {
        return "NoSQL RepositoryImpl";
    }
}
