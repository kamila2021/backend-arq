package com.arquitectura.proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private String unidadMedida;
    private Integer stockDisponible;

    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SolicitudInsumo> solicitudes = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insumo insumo = (Insumo) o;
        return Objects.equals(id, insumo.id) &&
                Objects.equals(nombre, insumo.nombre) &&
                Objects.equals(tipo, insumo.tipo) &&
                Objects.equals(unidadMedida, insumo.unidadMedida) &&
                Objects.equals(stockDisponible, insumo.stockDisponible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipo, unidadMedida, stockDisponible);
    }

    @Override
    public String toString() {
        return "Insumo{id=" + id +
                ", nombre='" + nombre + "'" +
                ", tipo='" + tipo + "'" +
                ", unidadMedida='" + unidadMedida + "'" +
                ", stockDisponible=" + stockDisponible + "}";
    }
}
