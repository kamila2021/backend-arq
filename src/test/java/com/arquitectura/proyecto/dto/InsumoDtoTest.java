package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsumoDtoTest {

    @Test
    void testInsumoDto() {
        // Crear y configurar InsumoDto
        InsumoDto dto = new InsumoDto();
        dto.setId(1L);
        dto.setNombre("Insumo Test");
        dto.setTipo("Tipo Test");
        dto.setUnidadMedida("Unidad Test");
        dto.setStockDisponible(100);

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals("Insumo Test", dto.getNombre());
        assertEquals("Tipo Test", dto.getTipo());
        assertEquals("Unidad Test", dto.getUnidadMedida());
        assertEquals(100, dto.getStockDisponible());

        // Verificar toString
        assertNotNull(dto.toString());
    }
} 