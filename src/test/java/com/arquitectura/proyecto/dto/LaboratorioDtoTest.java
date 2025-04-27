package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LaboratorioDtoTest {

    @Test
    void testLaboratorioDto() {
        // Crear y configurar LaboratorioDto
        LaboratorioDto dto = new LaboratorioDto();
        dto.setId(1L);
        dto.setNombre("Laboratorio de Química");
        dto.setCodigo("LAB-Q1");

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals("Laboratorio de Química", dto.getNombre());
        assertEquals("LAB-Q1", dto.getCodigo());

        // Verificar toString
        assertNotNull(dto.toString());

        // Probar constructor con todos los campos
        LaboratorioDto dto2 = new LaboratorioDto();
        dto2.setId(2L);
        dto2.setNombre("Laboratorio de Física");
        dto2.setCodigo("LAB-F1");

        // Verificar equals y hashCode
        assertNotEquals(dto, dto2);
        LaboratorioDto dto3 = new LaboratorioDto();
        dto3.setId(1L);
        dto3.setNombre("Laboratorio de Química");
        dto3.setCodigo("LAB-Q1");
        assertEquals(dto, dto3);
        assertEquals(dto.hashCode(), dto3.hashCode());
    }
} 