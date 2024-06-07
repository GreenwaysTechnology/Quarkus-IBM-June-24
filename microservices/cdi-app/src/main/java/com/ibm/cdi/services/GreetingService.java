package com.ibm.cdi.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String hello() {
        return "Hello from Quarkus REST";

    }
}
