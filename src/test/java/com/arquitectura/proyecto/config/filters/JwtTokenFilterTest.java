package com.arquitectura.proyecto.config.filters;

import com.arquitectura.proyecto.security.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternal_WhitelistedPath() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/login");

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtTokenProvider);
    }

    @Test
    void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/api/test");
        request.addHeader("Authorization", "Bearer valid_token");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        
        when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
        when(jwtTokenProvider.getUsernameAndPasswordAuth(anyString())).thenReturn(auth);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtTokenProvider).validateToken("valid_token");
        verify(jwtTokenProvider).getUsernameAndPasswordAuth("valid_token");
        assertEquals(auth, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/api/test");
        request.addHeader("Authorization", "Bearer invalid_token");
        
        when(jwtTokenProvider.validateToken(anyString())).thenReturn(false);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        verify(filterChain, never()).doFilter(any(), any());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/api/test");

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        verify(filterChain, never()).doFilter(any(), any());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidAuthorizationHeaderFormat() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/api/test");
        request.addHeader("Authorization", "InvalidFormat token");

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        verify(filterChain, never()).doFilter(any(), any());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_TokenValidationThrowsException() throws ServletException, IOException {
        // Arrange
        request.setServletPath("/api/test");
        request.addHeader("Authorization", "Bearer valid_token");
        
        when(jwtTokenProvider.validateToken(anyString())).thenThrow(new IllegalArgumentException("Token inválido"));

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        verify(filterChain, never()).doFilter(any(), any());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_MultipleWhitelistedPaths() throws ServletException, IOException {
        // Test multiple whitelisted paths
        String[] whitelistedPaths = {"/login", "/logout", "/graphiql", "/swagger-ui.html"};
        
        for (String path : whitelistedPaths) {
            // Arrange
            MockHttpServletRequest newRequest = new MockHttpServletRequest();
            MockHttpServletResponse newResponse = new MockHttpServletResponse();
            newRequest.setServletPath(path);
            
            // Act
            jwtTokenFilter.doFilterInternal(newRequest, newResponse, filterChain);
            
            // Assert
            verify(filterChain).doFilter(newRequest, newResponse);
        }
        
        verifyNoInteractions(jwtTokenProvider);
    }
} 