package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class UsuarioDtoTest {

    @Test
    void testUsuarioDto() {
        // Crear y configurar UsuarioDto
        UsuarioDto dto = new UsuarioDto();
        dto.setId(1L);
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setEmail("juan.perez@example.com");
        dto.setPassword("password123");
        dto.setAccountLocked(false);
        dto.setEnabled(true);
        List<Long> roles = Arrays.asList(1L, 2L);
        dto.setRoles(roles);

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals("Juan", dto.getNombre());
        assertEquals("Pérez", dto.getApellido());
        assertEquals("juan.perez@example.com", dto.getEmail());
        assertEquals("password123", dto.getPassword());
        assertFalse(dto.isAccountLocked());
        assertTrue(dto.isEnabled());
        assertEquals(roles, dto.getRoles());

        // Verificar toString
        assertNotNull(dto.toString());

        // Probar otro DTO para comparación
        UsuarioDto dto2 = new UsuarioDto();
        dto2.setId(2L);
        dto2.setNombre("María");
        dto2.setApellido("García");
        dto2.setEmail("maria.garcia@example.com");
        dto2.setPassword("password456");
        dto2.setAccountLocked(true);
        dto2.setEnabled(false);
        List<Long> roles2 = Arrays.asList(2L, 3L);
        dto2.setRoles(roles2);

        // Verificar equals y hashCode
        assertNotEquals(dto, dto2);
        UsuarioDto dto3 = new UsuarioDto();
        dto3.setId(1L);
        dto3.setNombre("Juan");
        dto3.setApellido("Pérez");
        dto3.setEmail("juan.perez@example.com");
        dto3.setPassword("password123");
        dto3.setAccountLocked(false);
        dto3.setEnabled(true);
        dto3.setRoles(roles);
        assertEquals(dto, dto3);
        assertEquals(dto.hashCode(), dto3.hashCode());
    }

    @Test
    void testUsuarioDtoWithNullValues() {
        UsuarioDto dto = new UsuarioDto();
        
        assertNull(dto.getId());
        assertNull(dto.getNombre());
        assertNull(dto.getApellido());
        assertNull(dto.getEmail());
        assertNull(dto.getPassword());
        assertFalse(dto.isAccountLocked());
        assertFalse(dto.isEnabled());
        assertNull(dto.getRoles());
    }
} 