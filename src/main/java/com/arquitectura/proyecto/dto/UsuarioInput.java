package com.arquitectura.proyecto.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioInput {
    private String nombre;
    private String email;
    private String password;
    private Set<Long> roleIds;
} 