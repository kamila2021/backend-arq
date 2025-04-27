package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsignaturaDtoTest {

    @Test
    void testGettersAndSetters() {
        AsignaturaDto asignatura = new AsignaturaDto();
        asignatura.setId(1L);
        asignatura.setName("Matemáticas");
        asignatura.setCode("MAT101");

        assertEquals(1L, asignatura.getId());
        assertEquals("Matemáticas", asignatura.getName());
        assertEquals("MAT101", asignatura.getCode());
    }

    @Test
    void testEqualsAndHashCode() {
        AsignaturaDto asignatura1 = new AsignaturaDto();
        asignatura1.setId(1L);
        asignatura1.setName("Física");
        asignatura1.setCode("FIS101");

        AsignaturaDto asignatura2 = new AsignaturaDto();
        asignatura2.setId(1L);
        asignatura2.setName("Física");
        asignatura2.setCode("FIS101");

        AsignaturaDto asignatura3 = new AsignaturaDto();
        asignatura3.setId(2L);
        asignatura3.setName("Química");
        asignatura3.setCode("QUI101");

        // Test equals
        assertEquals(asignatura1, asignatura2);
        assertNotEquals(asignatura1, asignatura3);

        // Test hashCode
        assertEquals(asignatura1.hashCode(), asignatura2.hashCode());
        assertNotEquals(asignatura1.hashCode(), asignatura3.hashCode());
    }

    @Test
    void testBuilder() {
        AsignaturaDto asignatura = AsignaturaDto.builder()
                .id(1L)
                .name("Biología")
                .code("BIO101")
                .build();

        assertEquals(1L, asignatura.getId());
        assertEquals("Biología", asignatura.getName());
        assertEquals("BIO101", asignatura.getCode());
    }

    @Test
    void testToString() {
        AsignaturaDto asignatura = new AsignaturaDto();
        asignatura.setId(1L);
        asignatura.setName("Matemáticas");
        asignatura.setCode("MAT101");

        String toString = asignatura.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name=Matemáticas"));
        assertTrue(toString.contains("code=MAT101"));
    }
} 