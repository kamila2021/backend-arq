package com.arquitectura.proyecto.config.provider;

import com.arquitectura.proyecto.service.UserDetailsImpl;
import com.arquitectura.proyecto.service.UsuarioDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomAuthenticationProviderTest {

    @Mock
    private UsuarioDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomAuthenticationProvider authenticationProvider;

    private UserDetailsImpl userDetails;
    private UsernamePasswordAuthenticationToken authenticationToken;

    @BeforeEach
    void setUp() {
        // Crear autoridades
        Collection<GrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority("ROLE_ADMIN")
        );

        // Crear UserDetailsImpl
        userDetails = new UserDetailsImpl(
            1L,
            "Test",
            "User",
            "test@example.com",
            "test@example.com",
            "encodedPassword",
            true,
            true,
            true,
            true,
            authorities,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
        );

        // Crear token de autenticación
        authenticationToken = new UsernamePasswordAuthenticationToken(
            "test@example.com",
            "password"
        );

        // Inicializar el provider
        authenticationProvider = new CustomAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Test
    void testAuthenticateSuccess() {
        // Configurar mocks
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Ejecutar autenticación
        Authentication result = authenticationProvider.authenticate(authenticationToken);

        // Verificar resultado
        assertNotNull(result);
        assertTrue(result.isAuthenticated());
        assertEquals(userDetails, result.getPrincipal());
        verify(userDetailsService, times(1)).loadUserByUsername(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @Test
    void testAuthenticateFailure() {
        // Configurar mocks
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Verificar que se lanza la excepción
        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(authenticationToken);
        });
        verify(userDetailsService, times(1)).loadUserByUsername(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @Test
    void testAuthenticateUserNotFound() {
        // Configurar mocks
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);

        // Verificar que se lanza la excepción
        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(authenticationToken);
        });
        verify(userDetailsService, times(1)).loadUserByUsername(anyString());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void testSupports() {
        assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
        assertFalse(authenticationProvider.supports(Authentication.class));
    }
} 