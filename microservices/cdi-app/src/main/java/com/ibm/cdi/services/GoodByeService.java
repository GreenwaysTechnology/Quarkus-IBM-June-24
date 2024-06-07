package com.ibm.cdi.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GoodByeService {

    public String sayGoodBye() {
        return "GoodBye";
    }
}
