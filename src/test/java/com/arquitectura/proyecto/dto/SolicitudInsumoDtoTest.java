package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolicitudInsumoDtoTest {

    @Test
    void testSolicitudInsumoDto() {
        // Crear un InsumoDto para la prueba
        InsumoDto insumoDto = new InsumoDto();
        insumoDto.setId(1L);
        insumoDto.setNombre("Insumo Test");
        insumoDto.setTipo("Tipo Test");
        insumoDto.setUnidadMedida("Unidad Test");
        insumoDto.setStockDisponible(100);

        // Crear y configurar SolicitudInsumoDto
        SolicitudInsumoDto dto = new SolicitudInsumoDto();
        dto.setId(1L);
        dto.setInsumo(insumoDto);
        dto.setCantidad(5.0);

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals(insumoDto, dto.getInsumo());
        assertEquals(5.0, dto.getCantidad());

        // Verificar toString
        assertNotNull(dto.toString());
    }
} 