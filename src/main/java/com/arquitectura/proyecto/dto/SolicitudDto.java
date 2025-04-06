package com.arquitectura.proyecto.dto;

import lombok.Data;

import java.util.List;

@Data
public class SolicitudDto {
    private Long id;
    private String fechaSolicitud;
    private String fechaUso;
    private String horario;
    private Integer cantGrupos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Integer getCantGrupos() {
        return cantGrupos;
    }

    public void setCantGrupos(Integer cantGrupos) {
        this.cantGrupos = cantGrupos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public AsignaturaDto getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(AsignaturaDto asignatura) {
        this.asignatura = asignatura;
    }

    public LaboratorioDto getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDto laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<SolicitudInsumoDto> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<SolicitudInsumoDto> insumos) {
        this.insumos = insumos;
    }

    private Boolean estado;

    private UsuarioDto usuario;
    private AsignaturaDto asignatura;
    private LaboratorioDto laboratorio;

    private List<SolicitudInsumoDto> insumos;
}
