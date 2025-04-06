package com.arquitectura.proyecto.dto;

import lombok.Data;

@Data
public class InsumoCantidadInput {
    private Long idInsumo;

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    private Double cantidad;
}
