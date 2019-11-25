package com.mssql.beadando.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reviewer {

    @Id
    private Long id;
    private String username;

    public Reviewer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
