package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class RolTest {

    @Test
    void testNoArgsConstructor() {
        Rol rol = new Rol();
        
        assertNotNull(rol);
        assertNull(rol.getId());
        assertNull(rol.getName());
        assertNotNull(rol.getUsuarios());
        assertTrue(rol.getUsuarios().isEmpty());
    }

    @Test
    void testBuilder() {
        Set<Usuario> usuarios = new HashSet<>();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuarios.add(usuario);

        Rol rol = Rol.builder()
                .id(1L)
                .name("ADMIN")
                .usuarios(usuarios)
                .build();

        assertEquals(1L, rol.getId());
        assertEquals("ADMIN", rol.getName());
        assertEquals(usuarios, rol.getUsuarios());
        assertEquals(1, rol.getUsuarios().size());
    }

    @Test
    void testGettersAndSetters() {
        Rol rol = new Rol();
        
        rol.setId(1L);
        rol.setName("USER");

        Set<Usuario> usuarios = new HashSet<>();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuarios.add(usuario);
        rol.setUsuarios(usuarios);

        assertEquals(1L, rol.getId());
        assertEquals("USER", rol.getName());
        assertEquals(usuarios, rol.getUsuarios());
        assertEquals(1, rol.getUsuarios().size());
        assertTrue(rol.getUsuarios().contains(usuario));
    }

    @Test
    void testEqualsAndHashCode() {
        Rol rol1 = new Rol();
        rol1.setId(1L);
        rol1.setName("ADMIN");

        Rol rol2 = new Rol();
        rol2.setId(1L);
        rol2.setName("ADMIN");

        assertEquals(rol1, rol2);
        assertEquals(rol1.hashCode(), rol2.hashCode());

        rol2.setId(2L);
        assertNotEquals(rol1, rol2);
        assertNotEquals(rol1.hashCode(), rol2.hashCode());
    }

    @Test
    void testToString() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName("ADMIN");

        String toString = rol.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name='ADMIN'"));
        assertEquals("Rol{id=1, name='ADMIN'}", toString);
    }

    @Test
    void testRolWithNullValues() {
        Rol rol = new Rol();
        
        assertNull(rol.getId());
        assertNull(rol.getName());
        assertNotNull(rol.getUsuarios());
        assertTrue(rol.getUsuarios().isEmpty());
    }
} 