package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testLoginRequestSettersAndGetters() {
        LoginRequest loginRequest = new LoginRequest();
        
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        assertEquals("test@example.com", loginRequest.getEmail());
        assertEquals("password123", loginRequest.getPassword());
    }

    @Test
    void testLoginRequestEqualsAndHashCode() {
        LoginRequest request1 = new LoginRequest();
        request1.setEmail("test@example.com");
        request1.setPassword("password123");

        LoginRequest request2 = new LoginRequest();
        request2.setEmail("test@example.com");
        request2.setPassword("password123");

        LoginRequest request3 = new LoginRequest();
        request3.setEmail("other@example.com");
        request3.setPassword("different123");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1, request3);
        assertNotEquals(request1.hashCode(), request3.hashCode());
    }

    @Test
    void testLoginRequestToString() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        String toString = loginRequest.toString();
        
        assertTrue(toString.contains("email=test@example.com"));
        // No debería mostrar la contraseña en toString por seguridad
        assertFalse(toString.contains("password123"));
    }

    @Test
    void testLoginRequestWithNullValues() {
        LoginRequest loginRequest = new LoginRequest();
        
        assertNull(loginRequest.getEmail());
        assertNull(loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithEmptyStrings() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("");
        
        assertEquals("", loginRequest.getEmail());
        assertEquals("", loginRequest.getPassword());
    }

    @Test
    void testLoginRequestWithSpecialCharacters() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user+test@example.com");
        loginRequest.setPassword("pass@123!#$");
        
        assertEquals("user+test@example.com", loginRequest.getEmail());
        assertEquals("pass@123!#$", loginRequest.getPassword());
    }
} 