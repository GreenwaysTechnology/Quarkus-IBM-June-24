package com.ibm.reactive.data.activerecord;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Customer extends PanacheEntity {
    @Column(name = "name")
    public String name;
    @Column(name = "city")
    public String city;
}
