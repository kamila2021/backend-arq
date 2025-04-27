package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class UsuarioTest {

    @Test
    void testUsuario() {
        // Crear roles
        Rol rol1 = new Rol();
        rol1.setId(1L);
        rol1.setName("ROLE_USER");

        Rol rol2 = new Rol();
        rol2.setId(2L);
        rol2.setName("ROLE_ADMIN");

        Set<Rol> roles = new HashSet<>();
        roles.add(rol1);
        roles.add(rol2);

        // Crear y configurar Usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setEmail("juan.perez@example.com");
        usuario.setPassword("password123");
        usuario.setAccountLocked(false);
        usuario.setEnabled(true);
        usuario.setResetPasswordToken("token123");
        usuario.setRoles(roles);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setLastModifiedDate(LocalDateTime.now());

        // Verificar los valores
        assertEquals(1L, usuario.getId());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
        assertEquals("juan.perez@example.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
        assertFalse(usuario.isAccountLocked());
        assertTrue(usuario.isEnabled());
        assertEquals("token123", usuario.getResetPasswordToken());
        assertEquals(roles, usuario.getRoles());
        assertNotNull(usuario.getCreatedAt());
        assertNotNull(usuario.getLastModifiedDate());

        // Verificar el método fullname
        assertEquals("Juan Pérez", usuario.fullname());

        // Probar constructor con todos los campos
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("María");
        usuario2.setApellido("González");
        usuario2.setEmail("maria.gonzalez@example.com");
        usuario2.setPassword("password456");
        usuario2.setAccountLocked(true);
        usuario2.setEnabled(false);
        usuario2.setResetPasswordToken("token456");
        usuario2.setRoles(new HashSet<>());
        usuario2.setCreatedAt(LocalDateTime.now());
        usuario2.setLastModifiedDate(LocalDateTime.now());

        // Verificar equals y hashCode
        assertNotEquals(usuario, usuario2);
        Usuario usuario3 = new Usuario();
        usuario3.setId(1L);
        usuario3.setNombre("Juan");
        usuario3.setApellido("Pérez");
        usuario3.setEmail("juan.perez@example.com");
        usuario3.setPassword("password123");
        usuario3.setAccountLocked(false);
        usuario3.setEnabled(true);
        usuario3.setResetPasswordToken("token123");
        usuario3.setRoles(roles);
        usuario3.setCreatedAt(usuario.getCreatedAt());
        usuario3.setLastModifiedDate(usuario.getLastModifiedDate());
        assertEquals(usuario, usuario3);
        assertEquals(usuario.hashCode(), usuario3.hashCode());
    }

    @Test
    void testUsuarioWithNullValues() {
        Usuario usuario = new Usuario();
        
        assertNull(usuario.getId());
        assertNull(usuario.getNombre());
        assertNull(usuario.getApellido());
        assertNull(usuario.getEmail());
        assertNull(usuario.getPassword());
        assertFalse(usuario.isAccountLocked());
        assertFalse(usuario.isEnabled());
        assertNull(usuario.getResetPasswordToken());
        assertNotNull(usuario.getRoles());
        assertNull(usuario.getCreatedAt());
        assertNull(usuario.getLastModifiedDate());
    }

    @Test
    void testUsuarioRoles() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");

        // Probar manejo de roles
        Rol rolUser = new Rol();
        rolUser.setId(1L);
        rolUser.setName("ROLE_USER");

        Rol rolAdmin = new Rol();
        rolAdmin.setId(2L);
        rolAdmin.setName("ROLE_ADMIN");

        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);

        assertEquals(1, usuario.getRoles().size());
        assertTrue(usuario.getRoles().contains(rolUser));

        // Agregar otro rol
        roles.add(rolAdmin);
        usuario.setRoles(roles);

        assertEquals(2, usuario.getRoles().size());
        assertTrue(usuario.getRoles().contains(rolAdmin));

        // Verificar que los roles son únicos
        roles.add(rolUser); // Intentar agregar el mismo rol
        assertEquals(2, usuario.getRoles().size());
    }
} 