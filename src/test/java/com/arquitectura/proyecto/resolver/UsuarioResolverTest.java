package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.dto.UsuarioDto;
import com.arquitectura.proyecto.dto.UsuarioInput;
import com.arquitectura.proyecto.model.Usuario;
import com.arquitectura.proyecto.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioResolverTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioResolver usuarioResolver;

    private Usuario usuario;
    private UsuarioDto usuarioInput;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        usuarioInput = new UsuarioDto();
        usuarioInput.setNombre("Usuario Test");
        usuarioInput.setEmail("test@example.com");
        usuarioInput.setPassword("password");
    }

    @Test
    void listarUsuarios_Success() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioService.listarUsuarios()).thenReturn(usuarios);

        List<Usuario> result = usuarioResolver.listarUsuarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuario.getNombre(), result.get(0).getNombre());
        verify(usuarioService, times(1)).listarUsuarios();
    }

    @Test
    void listarUsuarios_EmptyList() {
        when(usuarioService.listarUsuarios()).thenReturn(Collections.emptyList());

        List<Usuario> result = usuarioResolver.listarUsuarios();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(usuarioService, times(1)).listarUsuarios();
    }

    @Test
    void crearUsuario_Success() {
        when(usuarioService.crearUsuario(any())).thenReturn(usuario);

        Usuario result = usuarioResolver.crearUsuario(usuarioInput);

        assertNotNull(result);
        assertEquals(usuario.getNombre(), result.getNombre());
        assertEquals(usuario.getEmail(), result.getEmail());
        verify(usuarioService, times(1)).crearUsuario(any());
    }

    @Test
    void crearUsuario_DuplicateEmail() {
        when(usuarioService.crearUsuario(any())).thenThrow(new IllegalArgumentException("Email already exists"));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioResolver.crearUsuario(usuarioInput);
        });
        verify(usuarioService, times(1)).crearUsuario(any());
    }

    @Test
    void modificarUsuario_Success() {
        when(usuarioService.actualizarUsuario(any())).thenReturn(usuarioInput);

        UsuarioDto result = usuarioResolver.editarUsuario(usuarioInput);

        assertNotNull(result);
        assertEquals(usuario.getNombre(), result.getNombre());
        verify(usuarioService, times(1)).actualizarUsuario(any());
    }

    @Test
    void modificarUsuario_NotFound() {
        when(usuarioService.actualizarUsuario(any())).thenThrow(new IllegalArgumentException("User not found"));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioResolver.editarUsuario(usuarioInput);
        });
        verify(usuarioService, times(1)).actualizarUsuario(any());
    }

    @Test
    void eliminarUsuario_Success() {
        ResponseEntity<Boolean> response = ResponseEntity.ok(true);
        when(usuarioService.eliminarUsuario(anyLong())).thenReturn(response);

        Boolean result = usuarioResolver.eliminarUsuario(1L);

        assertTrue(result);
        verify(usuarioService, times(1)).eliminarUsuario(anyLong());
    }

    @Test
    void eliminarUsuario_NotFound() {
        when(usuarioService.eliminarUsuario(anyLong())).thenThrow(new IllegalArgumentException("User not found"));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioResolver.eliminarUsuario(1L);
        });
        verify(usuarioService, times(1)).eliminarUsuario(anyLong());
    }

    @Test
    void obtenerUsuarioPorId_Success() {
        when(usuarioService.obtenerUsuario(anyLong())).thenReturn(usuario);

        Usuario result = usuarioResolver.obtenerUsuario(1L);

        assertNotNull(result);
        assertEquals(usuario.getNombre(), result.getNombre());
        verify(usuarioService, times(1)).obtenerUsuario(anyLong());
    }

    @Test
    void obtenerUsuarioPorId_NotFound() {
        when(usuarioService.obtenerUsuario(anyLong())).thenThrow(new IllegalArgumentException("User not found"));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioResolver.obtenerUsuario(1L);
        });
        verify(usuarioService, times(1)).obtenerUsuario(anyLong());
    }

    @Test
    void obtenerUsuarioPorId_AccessDenied() {
        when(usuarioService.obtenerUsuario(anyLong())).thenThrow(new AccessDeniedException("Access denied"));

        assertThrows(AccessDeniedException.class, () -> {
            usuarioResolver.obtenerUsuario(1L);
        });
        verify(usuarioService, times(1)).obtenerUsuario(anyLong());
    }
} 