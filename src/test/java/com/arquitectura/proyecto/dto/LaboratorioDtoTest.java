package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LaboratorioDtoTest {

    @Test
    void testLaboratorioDtoSettersAndGetters() {
        LaboratorioDto laboratorio = new LaboratorioDto();
        
        laboratorio.setId(1L);
        laboratorio.setNombre("Laboratorio de Química");
        laboratorio.setCodigo("LAB-Q101");

        assertEquals(1L, laboratorio.getId());
        assertEquals("Laboratorio de Química", laboratorio.getNombre());
        assertEquals("LAB-Q101", laboratorio.getCodigo());
    }

    @Test
    void testLaboratorioDtoEqualsAndHashCode() {
        LaboratorioDto laboratorio1 = new LaboratorioDto();
        laboratorio1.setId(1L);
        laboratorio1.setNombre("Laboratorio de Química");
        laboratorio1.setCodigo("LAB-Q101");

        LaboratorioDto laboratorio2 = new LaboratorioDto();
        laboratorio2.setId(1L);
        laboratorio2.setNombre("Laboratorio de Química");
        laboratorio2.setCodigo("LAB-Q101");

        LaboratorioDto laboratorio3 = new LaboratorioDto();
        laboratorio3.setId(2L);
        laboratorio3.setNombre("Laboratorio de Física");
        laboratorio3.setCodigo("LAB-F101");

        assertEquals(laboratorio1, laboratorio2);
        assertEquals(laboratorio1.hashCode(), laboratorio2.hashCode());
        assertNotEquals(laboratorio1, laboratorio3);
        assertNotEquals(laboratorio1.hashCode(), laboratorio3.hashCode());
    }

    @Test
    void testLaboratorioDtoToString() {
        LaboratorioDto laboratorio = new LaboratorioDto();
        laboratorio.setId(1L);
        laboratorio.setNombre("Laboratorio de Química");
        laboratorio.setCodigo("LAB-Q101");

        String toString = laboratorio.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("nombre=Laboratorio de Química"));
        assertTrue(toString.contains("codigo=LAB-Q101"));
    }

    @Test
    void testLaboratorioDtoWithNullValues() {
        LaboratorioDto laboratorio = new LaboratorioDto();
        
        assertNull(laboratorio.getId());
        assertNull(laboratorio.getNombre());
        assertNull(laboratorio.getCodigo());
    }

    @Test
    void testLaboratorioDtoWithEmptyStrings() {
        LaboratorioDto laboratorio = new LaboratorioDto();
        laboratorio.setNombre("");
        laboratorio.setCodigo("");
        
        assertEquals("", laboratorio.getNombre());
        assertEquals("", laboratorio.getCodigo());
    }
} 