package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDtoTest {

    @Test
    void testUsuarioDtoSettersAndGetters() {
        UsuarioDto usuario = new UsuarioDto();
        List<Long> roles = Arrays.asList(1L, 2L);

        usuario.setId(1L);
        usuario.setNombre("María");
        usuario.setApellido("González");
        usuario.setEmail("maria.gonzalez@example.com");
        usuario.setPassword("password123");
        usuario.setEnabled(true);
        usuario.setAccountLocked(false);
        usuario.setRoles(roles);

        assertEquals(1L, usuario.getId());
        assertEquals("María", usuario.getNombre());
        assertEquals("González", usuario.getApellido());
        assertEquals("maria.gonzalez@example.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
        assertTrue(usuario.isEnabled());
        assertFalse(usuario.isAccountLocked());
        assertEquals(roles, usuario.getRoles());
    }

    @Test
    void testUsuarioDtoEqualsAndHashCode() {
        UsuarioDto usuario1 = new UsuarioDto();
        usuario1.setId(1L);
        usuario1.setNombre("Juan");
        usuario1.setApellido("Pérez");
        usuario1.setEmail("juan.perez@example.com");
        usuario1.setPassword("password123");
        usuario1.setRoles(Arrays.asList(1L));

        UsuarioDto usuario2 = new UsuarioDto();
        usuario2.setId(1L);
        usuario2.setNombre("Juan");
        usuario2.setApellido("Pérez");
        usuario2.setEmail("juan.perez@example.com");
        usuario2.setPassword("password123");
        usuario2.setRoles(Arrays.asList(1L));

        UsuarioDto usuario3 = new UsuarioDto();
        usuario3.setId(2L);
        usuario3.setNombre("María");
        usuario3.setApellido("González");
        usuario3.setEmail("maria.gonzalez@example.com");
        usuario3.setPassword("password456");
        usuario3.setRoles(Arrays.asList(2L));

        assertEquals(usuario1, usuario2);
        assertEquals(usuario1.hashCode(), usuario2.hashCode());
        assertNotEquals(usuario1, usuario3);
        assertNotEquals(usuario1.hashCode(), usuario3.hashCode());
    }

    @Test
    void testUsuarioDtoToString() {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setEmail("juan.perez@example.com");

        String toString = usuario.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("nombre=Juan"));
        assertTrue(toString.contains("apellido=Pérez"));
        assertTrue(toString.contains("email=juan.perez@example.com"));
    }

    @Test
    void testUsuarioDtoWithNullValues() {
        UsuarioDto usuario = new UsuarioDto();
        
        assertNull(usuario.getId());
        assertNull(usuario.getNombre());
        assertNull(usuario.getApellido());
        assertNull(usuario.getEmail());
        assertNull(usuario.getPassword());
        assertFalse(usuario.isAccountLocked());
        assertFalse(usuario.isEnabled());
        assertNull(usuario.getRoles());
    }
} 