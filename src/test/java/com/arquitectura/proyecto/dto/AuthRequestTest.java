package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    void testAuthRequest() {
        // Crear y configurar AuthRequest
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // Verificar los valores
        assertEquals("test@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());

        // Verificar toString
        assertNotNull(request.toString());
    }
} 