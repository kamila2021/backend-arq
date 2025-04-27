package com.arquitectura.proyecto.model;

import com.arquitectura.proyecto.Enum.EnumRol;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean accountLocked;

    private boolean enabled;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rol> roles = new HashSet<>();

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    public String fullname() {
        return nombre + " " + (apellido != null ? apellido : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return accountLocked == usuario.accountLocked &&
               enabled == usuario.enabled &&
               Objects.equals(id, usuario.id) &&
               Objects.equals(nombre, usuario.nombre) &&
               Objects.equals(apellido, usuario.apellido) &&
               Objects.equals(email, usuario.email) &&
               Objects.equals(password, usuario.password) &&
               Objects.equals(resetPasswordToken, usuario.resetPasswordToken) &&
               Objects.equals(roles, usuario.roles) &&
               Objects.equals(createdAt, usuario.createdAt) &&
               Objects.equals(lastModifiedDate, usuario.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, password, accountLocked, enabled,
                          resetPasswordToken, roles, createdAt, lastModifiedDate);
    }
}
