package com.arquitectura.proyecto.dto;

import lombok.Data;

import java.util.List;

@Data

public class CrearSolicitudInput {
    private Long idUsuario;
    private Long idAsignatura;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Long getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Long idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(String fechaUso) {
        this.fechaUso = fechaUso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCantGrupos() {
        return cantGrupos;
    }

    public void setCantGrupos(int cantGrupos) {
        this.cantGrupos = cantGrupos;
    }

    public List<InsumoCantidadInput> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<InsumoCantidadInput> insumos) {
        this.insumos = insumos;
    }

    private Long idLaboratorio;
    private String fechaUso; // formato "yyyy-MM-dd"
    private String horario;  // formato "HH:mm"
    private int cantGrupos;
    private List<InsumoCantidadInput> insumos;
}
