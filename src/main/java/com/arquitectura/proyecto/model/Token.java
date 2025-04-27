package com.arquitectura.proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    private int status = 1;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime expiresAt;

    public Token(String token, Usuario user, TokenType type) {
        this.token = token;
        this.user = user;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = this.createdAt.plusMinutes(15);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return status == token1.status &&
                Objects.equals(id, token1.id) &&
                Objects.equals(token, token1.token) &&
                type == token1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, type, status);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                ", type=" + type +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                '}';
    }
}