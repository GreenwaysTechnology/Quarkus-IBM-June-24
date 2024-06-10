package com.ibm.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//define enity in dao pattern
@Entity
public class Employee {
    @Id
    @GeneratedValue
    public Long id;
    @Column(name = "name")
    public String name;
    @Column(name = "city")
    public String city;

    public Employee() {
    }

    public Employee(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
