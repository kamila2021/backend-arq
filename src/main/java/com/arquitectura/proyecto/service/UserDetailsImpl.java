package com.arquitectura.proyecto.service;


import lombok.Data;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserDetailsImpl implements UserDetails {

    private final Collection<GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    Map<String, Object> parameters = new HashMap<>();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Long userId;
    private final Collection<UserDetailsRole> roles;
    private final Collection<String> permissions;
    private final Collection<Map<String, String>> options;

    @Generated
    public UserDetailsImpl(Long userId, String firstName, String lastName, String email, String username, String password,
                           boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                           Collection<GrantedAuthority> authorities, Collection<UserDetailsRole> roles, Collection<Map<String, String>> options, Collection<String> permissions) {
        this.authorities = authorities;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
        this.options = options;
        this.permissions = permissions;
    }

    @Generated
    public void putParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    @Generated
    public Object getParameter(String key) {
        return this.parameters.get(key);
    }
}