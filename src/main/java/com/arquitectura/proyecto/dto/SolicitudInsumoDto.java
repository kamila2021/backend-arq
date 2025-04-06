package com.arquitectura.proyecto.dto;

import lombok.Data;

@Data
public class SolicitudInsumoDto {
    private Long id;
    private InsumoDto insumo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsumoDto getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDto insumo) {
        this.insumo = insumo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    private Double cantidad;
}
