package com.shopping.citypoint.models;

import javax.persistence.*;

@Entity
public class Catagory {

    private @Id @GeneratedValue Long id;
    private String name;

    public Catagory() {
    }

    public Catagory(String name) {
        this.name = name;
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
}
