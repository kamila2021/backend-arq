package com.arquitectura.proyecto.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsRoleTest {

    @Test
    void constructorAndGetters_Success() {
        UserDetailsRole role = new UserDetailsRole(1L, "USER");

        assertEquals(1L, role.getId());
        assertEquals("USER", role.getName());
    }

    @Test
    void setters_Success() {
        UserDetailsRole role = new UserDetailsRole(1L, "USER");

        role.setId(2L);
        role.setName("ADMIN");

        assertEquals(2L, role.getId());
        assertEquals("ADMIN", role.getName());
    }
} 