package com.ibm.cdi.services.interfaces;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class ISBNGeneratorImpl implements NumberGenerator {
    @Override
    public String generateISBNGenerator() {
        return "15232323-45455" + Math.abs(new Random().nextInt());
    }
}
