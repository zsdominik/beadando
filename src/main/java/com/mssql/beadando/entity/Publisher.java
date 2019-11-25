package com.mssql.beadando.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publisher {

    @Id
    private Long id;
    private String name;

    public Publisher() {}

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
