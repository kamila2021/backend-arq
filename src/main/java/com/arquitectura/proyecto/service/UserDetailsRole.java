package com.arquitectura.proyecto.service;

import lombok.Data;
import lombok.Generated;

@Data
public class UserDetailsRole {
    Long id;
    String name;

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

    @Generated
    public UserDetailsRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
