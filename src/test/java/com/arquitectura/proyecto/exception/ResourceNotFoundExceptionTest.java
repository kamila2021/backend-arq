package com.arquitectura.proyecto.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String errorMessage = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);
        
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithResourceDetails() {
        String resourceName = "Usuario";
        String fieldName = "email";
        String fieldValue = "test@example.com";
        
        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);
        
        String expectedMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);
        assertEquals(expectedMessage, exception.getMessage());
    }
} 