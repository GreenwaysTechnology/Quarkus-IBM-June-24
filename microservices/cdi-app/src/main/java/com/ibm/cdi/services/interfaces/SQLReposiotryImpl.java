package com.ibm.cdi.services.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("sql")
public class SQLReposiotryImpl implements Repository{
    @Override
    public String findAll() {
        return "SQL Findall";
    }
}
