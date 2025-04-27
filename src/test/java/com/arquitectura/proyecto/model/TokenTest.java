package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testTokenConstructorAndGetters() {
        Usuario user = new Usuario();
        user.setId(1L);
        user.setEmail("test@example.com");

        Token token = new Token("testToken", user, TokenType.VALIDATION);

        assertEquals("testToken", token.getToken());
        assertEquals(user, token.getUser());
        assertEquals(TokenType.VALIDATION, token.getType());
        assertEquals(1, token.getStatus());
        assertNotNull(token.getCreatedAt());
        assertNotNull(token.getExpiresAt());
        assertTrue(token.getExpiresAt().isAfter(token.getCreatedAt()));
    }

    @Test
    void testTokenBuilder() {
        LocalDateTime now = LocalDateTime.now();
        Usuario user = new Usuario();
        user.setId(1L);

        Token token = Token.builder()
                .id(1L)
                .token("builderToken")
                .user(user)
                .type(TokenType.RESET_PASSWORD)
                .status(1)
                .createdAt(now)
                .expiresAt(now.plusMinutes(15))
                .build();

        assertEquals(1L, token.getId());
        assertEquals("builderToken", token.getToken());
        assertEquals(user, token.getUser());
        assertEquals(TokenType.RESET_PASSWORD, token.getType());
        assertEquals(1, token.getStatus());
        assertEquals(now, token.getCreatedAt());
        assertEquals(now.plusMinutes(15), token.getExpiresAt());
    }

    @Test
    void testTokenEqualsAndHashCode() {
        Usuario user = new Usuario();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        Token token1 = Token.builder()
                .id(1L)
                .token("token")
                .user(user)
                .type(TokenType.VALIDATION)
                .status(1)
                .createdAt(now)
                .expiresAt(now.plusMinutes(15))
                .build();

        Token token2 = Token.builder()
                .id(1L)
                .token("token")
                .user(user)
                .type(TokenType.VALIDATION)
                .status(1)
                .createdAt(now)
                .expiresAt(now.plusMinutes(15))
                .build();

        Token token3 = Token.builder()
                .id(2L)
                .token("differentToken")
                .user(user)
                .type(TokenType.REFRESH)
                .status(1)
                .createdAt(now)
                .expiresAt(now.plusMinutes(15))
                .build();

        assertEquals(token1, token2);
        assertEquals(token1.hashCode(), token2.hashCode());
        assertNotEquals(token1, token3);
        assertNotEquals(token1.hashCode(), token3.hashCode());
    }

    @Test
    void testTokenTypes() {
        Usuario user = new Usuario();
        
        Token validationToken = new Token("validation", user, TokenType.VALIDATION);
        Token resetToken = new Token("reset", user, TokenType.RESET_PASSWORD);
        Token refreshToken = new Token("refresh", user, TokenType.REFRESH);

        assertEquals(TokenType.VALIDATION, validationToken.getType());
        assertEquals(TokenType.RESET_PASSWORD, resetToken.getType());
        assertEquals(TokenType.REFRESH, refreshToken.getType());
    }

    @Test
    void testTokenExpiration() {
        Usuario user = new Usuario();
        Token token = new Token("test", user, TokenType.VALIDATION);
        
        LocalDateTime now = LocalDateTime.now();
        assertTrue(token.getExpiresAt().isAfter(now));
        assertTrue(token.getExpiresAt().isBefore(now.plusMinutes(16)));
    }

    @Test
    void testTokenStatus() {
        Usuario user = new Usuario();
        Token token = new Token("test", user, TokenType.VALIDATION);
        
        assertEquals(1, token.getStatus());
        token.setStatus(0);
        assertEquals(0, token.getStatus());
    }

    @Test
    void testTokenToString() {
        Usuario user = new Usuario();
        user.setId(1L);
        Token token = new Token("testToken", user, TokenType.VALIDATION);

        String toString = token.toString();
        
        assertTrue(toString.contains("testToken"));
        assertTrue(toString.contains("VALIDATION"));
        assertTrue(toString.contains("status=1"));
    }
} 