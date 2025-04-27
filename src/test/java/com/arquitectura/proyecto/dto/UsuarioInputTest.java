package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class UsuarioInputTest {

    @Test
    void testUsuarioInput() {
        // Crear y configurar UsuarioInput
        UsuarioInput input = new UsuarioInput();
        input.setNombre("Usuario Test");
        input.setEmail("test@example.com");
        input.setPassword("password123");
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(1L);
        roleIds.add(2L);
        input.setRoleIds(roleIds);

        // Verificar los valores
        assertEquals("Usuario Test", input.getNombre());
        assertEquals("test@example.com", input.getEmail());
        assertEquals("password123", input.getPassword());
        assertEquals(roleIds, input.getRoleIds());

        // Verificar toString
        assertNotNull(input.toString());
    }
} 