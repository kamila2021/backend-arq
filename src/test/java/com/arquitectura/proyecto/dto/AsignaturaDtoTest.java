package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsignaturaDtoTest {

    @Test
    void testAsignaturaDto() {
        // Crear y configurar AsignaturaDto
        AsignaturaDto dto = new AsignaturaDto();
        dto.setId(1L);
        dto.setNombre("Matemáticas");
        dto.setCodigo("MAT101");

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals("Matemáticas", dto.getNombre());
        assertEquals("MAT101", dto.getCodigo());

        // Verificar toString
        assertNotNull(dto.toString());

        // Probar constructor con todos los campos
        AsignaturaDto dto2 = new AsignaturaDto();
        dto2.setId(2L);
        dto2.setNombre("Física");
        dto2.setCodigo("FIS101");

        // Verificar equals y hashCode
        assertNotEquals(dto, dto2);
        AsignaturaDto dto3 = new AsignaturaDto();
        dto3.setId(1L);
        dto3.setNombre("Matemáticas");
        dto3.setCodigo("MAT101");
        assertEquals(dto, dto3);
        assertEquals(dto.hashCode(), dto3.hashCode());
    }
} 