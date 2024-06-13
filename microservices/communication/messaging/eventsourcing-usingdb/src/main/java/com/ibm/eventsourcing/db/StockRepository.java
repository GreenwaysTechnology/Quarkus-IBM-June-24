package com.ibm.eventsourcing.db;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

    public List findByName(String name) {
        return list("name", name);
    }
}