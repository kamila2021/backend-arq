package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.model.Rol;
import com.arquitectura.proyecto.model.Usuario;
import com.arquitectura.proyecto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioDetailsService usuarioDetailsService;

    private Usuario usuario;
    private Rol rol;

    @BeforeEach
    void setUp() {
        rol = new Rol();
        rol.setName("USER");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");
        usuario.setRoles(Set.of(rol));
    }

    @Test
    void loadUserByUsername_Success() {
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(usuario);

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals(usuario.getEmail(), userDetails.getUsername());
        assertEquals(usuario.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(usuarioRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            usuarioDetailsService.loadUserByUsername("nonexistent@example.com");
        });

        verify(usuarioRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void loadUserByUsername_MultipleRoles() {
        Rol rol2 = new Rol();
        rol2.setName("ADMIN");
        usuario.setRoles(Set.of(rol, rol2));

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(usuario);

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
    }
} 