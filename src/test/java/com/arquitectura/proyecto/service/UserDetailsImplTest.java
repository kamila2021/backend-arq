package com.arquitectura.proyecto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {

    private UserDetailsImpl userDetails;
    private Collection<GrantedAuthority> authorities;
    private Collection<UserDetailsRole> roles;
    private Collection<Map<String, String>> options;
    private Collection<String> permissions;

    @BeforeEach
    void setUp() {
        authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        roles = Arrays.asList(new UserDetailsRole(1L, "USER"));
        options = Arrays.asList(new HashMap<>());
        permissions = Arrays.asList("READ", "WRITE");

        userDetails = new UserDetailsImpl(
                1L,
                "John",
                "Doe",
                "john@example.com",
                "johndoe",
                "password",
                true,
                true,
                true,
                true,
                authorities,
                roles,
                options,
                permissions
        );
    }

    @Test
    void getAuthorities_Success() {
        Collection<GrantedAuthority> result = userDetails.getAuthorities();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void getPassword_Success() {
        assertEquals("password", userDetails.getPassword());
    }

    @Test
    void getUsername_Success() {
        assertEquals("johndoe", userDetails.getUsername());
    }

    @Test
    void isAccountNonExpired_Success() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_Success() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired_Success() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_Success() {
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void getParameters_Success() {
        Map<String, Object> parameters = userDetails.getParameters();
        assertNotNull(parameters);
        assertTrue(parameters.isEmpty());
    }

    @Test
    void setParameters_Success() {
        Map<String, Object> newParameters = new HashMap<>();
        newParameters.put("key", "value");
        userDetails.setParameters(newParameters);
        assertEquals(newParameters, userDetails.getParameters());
    }

    @Test
    void getFirstName_Success() {
        assertEquals("John", userDetails.getFirstName());
    }

    @Test
    void getLastName_Success() {
        assertEquals("Doe", userDetails.getLastName());
    }

    @Test
    void getEmail_Success() {
        assertEquals("john@example.com", userDetails.getEmail());
    }

    @Test
    void getUserId_Success() {
        assertEquals(1L, userDetails.getUserId());
    }

    @Test
    void getRoles_Success() {
        Collection<UserDetailsRole> result = userDetails.getRoles();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(role -> role.getName().equals("USER")));
    }

    @Test
    void getPermissions_Success() {
        Collection<String> result = userDetails.getPermissions();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("READ"));
        assertTrue(result.contains("WRITE"));
    }

    @Test
    void getOptions_Success() {
        Collection<Map<String, String>> result = userDetails.getOptions();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void putParameter_Success() {
        userDetails.putParameter("testKey", "testValue");
        assertEquals("testValue", userDetails.getParameter("testKey"));
    }

    @Test
    void getParameter_Success() {
        userDetails.putParameter("testKey", "testValue");
        assertEquals("testValue", userDetails.getParameter("testKey"));
    }

    @Test
    void getParameter_NotFound() {
        assertNull(userDetails.getParameter("nonexistentKey"));
    }
} 