package com.arquitectura.proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Solicitud.java
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    private LocalDate fechaSolicitud;
    private LocalDate fechaUso;
    private String horario;
    private Integer cantGrupos;
    private Boolean estado;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SolicitudInsumo> insumos = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(id, solicitud.id) &&
                Objects.equals(fechaSolicitud, solicitud.fechaSolicitud) &&
                Objects.equals(fechaUso, solicitud.fechaUso) &&
                Objects.equals(horario, solicitud.horario) &&
                Objects.equals(cantGrupos, solicitud.cantGrupos) &&
                Objects.equals(estado, solicitud.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaSolicitud, fechaUso, horario, cantGrupos, estado);
    }

    @Override
    public String toString() {
        return "Solicitud{id=" + id +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaUso=" + fechaUso +
                ", horario='" + horario + "'" +
                ", cantGrupos=" + cantGrupos +
                ", estado=" + estado + "}";
    }
}
