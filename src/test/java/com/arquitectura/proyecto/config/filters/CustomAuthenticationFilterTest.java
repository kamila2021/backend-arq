package com.arquitectura.proyecto.config.filters;

import com.arquitectura.proyecto.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomAuthenticationFilterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication authentication;

    private CustomAuthenticationFilter filter;
    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        filter = new CustomAuthenticationFilter(authenticationManager);

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
            "password",
            true,
            true,
            true,
            true,
            authorities,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    @Test
    void testAttemptAuthentication() throws Exception {
        // Configurar request
        when(request.getParameter("username")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(authentication);

        // Ejecutar el método
        filter.attemptAuthentication(request, response);

        // Verificar que se llamó al authenticationManager con los parámetros correctos
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testSuccessfulAuthentication() throws Exception {
        // Configurar mocks
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/login"));

        // Ejecutar el método
        filter.successfulAuthentication(request, response, filterChain, authentication);

        // Verificar que se escribió la respuesta
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        
        // Verificar que la respuesta contiene los campos necesarios
        String responseContent = stringWriter.toString();
        assertNotNull(responseContent);
        assertTrue(responseContent.contains("\"access_token\""));
        assertTrue(responseContent.contains("\"refresh_token\""));
        assertTrue(responseContent.contains("\"email\""));
        assertTrue(responseContent.contains("\"roles\""));
    }
} 