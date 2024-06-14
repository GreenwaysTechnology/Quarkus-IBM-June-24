package com.ibm.event.sourcing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue
    public long id;
    public String name;
    public int quantity;
    public String userName;
}
