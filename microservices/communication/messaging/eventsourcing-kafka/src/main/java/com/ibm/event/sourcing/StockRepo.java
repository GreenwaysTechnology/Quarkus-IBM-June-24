package com.ibm.event.sourcing;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StockRepo implements PanacheRepository<Stock> {
    //custom Query
    public Uni<List<Stock>> findByName(String name) {
        return list("name", name);
    }
}
