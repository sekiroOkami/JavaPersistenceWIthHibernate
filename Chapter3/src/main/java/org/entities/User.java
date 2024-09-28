package org.entities;

import jakarta.persistence.Entity;

@Entity
public class User {
    private String username;
    private String lastname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
