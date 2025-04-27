package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.model.Token;
import com.arquitectura.proyecto.model.TokenType;
import com.arquitectura.proyecto.model.Usuario;
import com.arquitectura.proyecto.repository.RoleRepository;
import com.arquitectura.proyecto.repository.TokenRepository;
import com.arquitectura.proyecto.repository.UsuarioRepository;
import com.arquitectura.proyecto.security.JwtTokenProvider;
import com.arquitectura.proyecto.exception.ResourceNotFoundException;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UsuarioRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(authenticationService, "activationUrl", "http://localhost:3000/activate");
    }

    @Test
    void testSendValidationEmail_Success() throws MessagingException {
        // Arrange
        String email = "test@example.com";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setId(1L);
        usuario.setApellido("Test");
        
        // Act
        authenticationService.sendValidationEmail(usuario);
        
        // Assert
        verify(emailService).sendEmail(
            eq(email),
            eq("Test"),
            anyString(),
            eq("Account activation")
        );
        
        // Verify token creation and save
        ArgumentCaptor<Token> tokenCaptor = ArgumentCaptor.forClass(Token.class);
        verify(tokenRepository).save(tokenCaptor.capture());
        Token savedToken = tokenCaptor.getValue();
        
        assertEquals(usuario, savedToken.getUser());
        assertEquals(1, savedToken.getStatus());
        assertNotNull(savedToken.getCreatedAt());
        assertNotNull(savedToken.getExpiresAt());
        assertNotNull(savedToken.getToken());
        assertEquals(6, savedToken.getToken().length());
        
        // Verify user update
        ArgumentCaptor<Usuario> userCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(userRepository).save(userCaptor.capture());
        Usuario savedUser = userCaptor.getValue();
        assertEquals(savedToken.getToken(), savedUser.getResetPasswordToken());
    }

    @Test
    void testSendValidationEmail_EmailServiceFailure() throws MessagingException {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setApellido("Test");

        doThrow(new RuntimeException("Email service error"))
            .when(emailService)
            .sendEmail(anyString(), anyString(), anyString(), anyString());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> authenticationService.sendValidationEmail(usuario));
        
        assertTrue(exception.getMessage().contains("Error: Email service error"));
    }

    @Test
    void testSendValidationEmail_NullUser() throws MessagingException {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> authenticationService.sendValidationEmail(null));
        
        // Verify
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString(), anyString());
        verify(tokenRepository, never()).save(any());
        verify(userRepository, never()).save(any());
    }
} 