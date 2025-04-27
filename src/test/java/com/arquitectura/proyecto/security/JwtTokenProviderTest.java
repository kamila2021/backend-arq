package com.arquitectura.proyecto.security;

import com.arquitectura.proyecto.model.Rol;
import com.arquitectura.proyecto.model.Token;
import com.arquitectura.proyecto.model.TokenType;
import com.arquitectura.proyecto.model.Usuario;
import com.arquitectura.proyecto.repository.TokenRepository;
import com.arquitectura.proyecto.repository.UsuarioRepository;
import com.arquitectura.proyecto.service.UserDetailsImpl;
import com.arquitectura.proyecto.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsImpl userDetails;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Crear un rol
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName("ROLE_USER");
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);

        // Crear usuario
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Test");
        usuario.setApellido("User");
        usuario.setEmail("test@example.com");
        usuario.setRoles(roles);

        // Crear UserDetailsImpl
        userDetails = new UserDetailsImpl(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getEmail(),
            usuario.getEmail(), // usando email como username
            "password",
            true, // enabled
            true, // accountNonExpired
            true, // accountNonLocked
            true, // credentialsNonExpired
            new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))),
            new ArrayList<>(), // roles
            new ArrayList<>(), // options
            new ArrayList<>()  // permissions
        );
    }

    @Test
    void testGenerateAccessToken_Success() {
        // Act
        String token = jwtTokenProvider.generateAccessToken(userDetails);

        // Assert
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void testGenerateRefreshToken_Success() {
        // Arrange
        when(usuarioRepository.getReferenceById(anyLong())).thenReturn(usuario);

        // Act
        String token = jwtTokenProvider.generateRefreshToken(userDetails);

        // Assert
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        verify(tokenRepository).save(argThat(t -> 
            t.getUser().equals(usuario) &&
            t.getStatus() == 1 &&
            t.getToken().equals(token)
        ));
    }

    @Test
    void testValidateToken_ValidToken() {
        // Arrange
        String token = jwtTokenProvider.generateAccessToken(userDetails);

        // Act & Assert
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_InvalidToken() {
        // Act & Assert
        assertFalse(jwtTokenProvider.validateToken("invalid.token.here"));
    }

    @Test
    void testGetUsernameAndPasswordAuth_InvalidToken() {
        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> jwtTokenProvider.getUsernameAndPasswordAuth("invalid.token.here"));
    }

    @Test
    void testRefreshTokens_InvalidToken() {
        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> jwtTokenProvider.refreshTokens("invalid.token.here"));
    }

    @Test
    void testGetUserIdFromToken_Success() throws Exception {
        // Arrange
        String token = jwtTokenProvider.generateAccessToken(userDetails);

        // Act
        Long userId = jwtTokenProvider.getUserIdFromToken(token);

        // Assert
        assertEquals(1L, userId);
    }

    @Test
    void testGetUserIdFromToken_InvalidToken() {
        // Act & Assert
        assertThrows(Exception.class,
            () -> jwtTokenProvider.getUserIdFromToken("invalid.token.here"));
    }

    @Test
    void testGenerateRefreshToken_NullUser() {
        // Act
        String token = jwtTokenProvider.generateRefreshToken(null);

        // Assert
        assertNull(token);
        verify(tokenRepository, never()).save(any());
    }
}