package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class RegisterRequestTest {

    @Test
    void testRegisterRequest() {
        // Crear y configurar RegisterRequest
        RegisterRequest request = new RegisterRequest();
        request.setNombre("Usuario Test");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(1L);
        roleIds.add(2L);
        request.setRoleIds(roleIds);

        // Verificar los valores
        assertEquals("Usuario Test", request.getNombre());
        assertEquals("test@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
        assertEquals(roleIds, request.getRoleIds());

        // Verificar toString
        assertNotNull(request.toString());
    }
} 