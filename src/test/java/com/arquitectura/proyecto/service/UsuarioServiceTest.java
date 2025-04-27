package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.UsuarioDto;
import com.arquitectura.proyecto.dto.UsuarioInput;
import com.arquitectura.proyecto.model.Rol;
import com.arquitectura.proyecto.model.Usuario;
import com.arquitectura.proyecto.repository.RoleRepository;
import com.arquitectura.proyecto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private Rol role;
    private UsuarioDto usuarioInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = new Rol();
        role.setId(1L);
        role.setName("ROLE_USER");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Test User");
        usuario.setEmail("test@example.com");
        usuario.setPassword("encodedPassword");
        usuario.setRoles(Set.of(role));

        usuarioInput = new UsuarioDto();
        usuarioInput.setId(1L);
        usuarioInput.setNombre("Test User");
        usuarioInput.setEmail("test@example.com");
        usuarioInput.setPassword("password");
        usuarioInput.setRoles(List.of(1L));
    }

    @Test
    void crearUsuario_Success() {
        when(roleRepository.existsById(1L)).thenReturn(true);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.crearUsuario(usuarioInput);

        assertNotNull(result);
        assertEquals(usuario.getEmail(), result.getEmail());
        assertEquals(usuario.getNombre(), result.getNombre());
        verify(usuarioRepository, times(2)).save(any(Usuario.class));
    }

    @Test
    void crearUsuario_RoleNotFound() {
        when(roleRepository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            usuarioService.crearUsuario(usuarioInput);
        });
    }

    @Test
    void obtenerUsuarioPorId_Success() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.obtenerUsuario(1L);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getEmail(), result.getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerUsuarioPorId_NotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.obtenerUsuario(1L);
        });
    }

    @Test
    void actualizarUsuario_Success() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDto result = usuarioService.actualizarUsuario(usuarioInput);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void eliminarUsuario_Success() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        ResponseEntity result = usuarioService.eliminarUsuario(1L);

        assertTrue((Boolean) result.getBody());
        verify(usuarioRepository, times(1)).existsById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void listarUsuarios_Success() {
        List<Usuario> usuarios = Collections.singletonList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.listarUsuarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuario.getId(), result.get(0).getId());
    }
} 