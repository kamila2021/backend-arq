package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AsignaturaTest {

    @Test
    void testBuilder() {
        Asignatura asignatura = Asignatura.builder()
                .id(1L)
                .name("Matemáticas")
                .code("MAT101")
                .build();

        assertEquals(1L, asignatura.getId());
        assertEquals("Matemáticas", asignatura.getName());
        assertEquals("MAT101", asignatura.getCode());
        assertNotNull(asignatura.getSolicitudes());
        assertTrue(asignatura.getSolicitudes().isEmpty());
    }

    @Test
    void testNoArgsConstructor() {
        Asignatura asignatura = new Asignatura();
        assertNotNull(asignatura);
        assertNull(asignatura.getId());
        assertNull(asignatura.getName());
        assertNull(asignatura.getCode());
        assertNotNull(asignatura.getSolicitudes());
        assertTrue(asignatura.getSolicitudes().isEmpty());
    }

    @Test
    void testAllArgsConstructor() {
        List<Solicitud> solicitudes = new ArrayList<>();
        Asignatura asignatura = new Asignatura(1L, "Física", "FIS101", solicitudes, null, null);

        assertEquals(1L, asignatura.getId());
        assertEquals("Física", asignatura.getName());
        assertEquals("FIS101", asignatura.getCode());
        assertSame(solicitudes, asignatura.getSolicitudes());
    }

    @Test
    void testGettersAndSetters() {
        Asignatura asignatura = new Asignatura();

        asignatura.setId(1L);
        asignatura.setName("Química");
        asignatura.setCode("QUI101");

        List<Solicitud> solicitudes = new ArrayList<>();
        Solicitud solicitud = new Solicitud();
        solicitudes.add(solicitud);
        asignatura.setSolicitudes(solicitudes);

        assertEquals(1L, asignatura.getId());
        assertEquals("Química", asignatura.getName());
        assertEquals("QUI101", asignatura.getCode());
        assertSame(solicitudes, asignatura.getSolicitudes());
        assertEquals(1, asignatura.getSolicitudes().size());
    }

    @Test
    void testSolicitudesListManagement() {
        Asignatura asignatura = new Asignatura();
        
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);
        
        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);

        asignatura.getSolicitudes().add(solicitud1);
        asignatura.getSolicitudes().add(solicitud2);

        assertEquals(2, asignatura.getSolicitudes().size());
        assertTrue(asignatura.getSolicitudes().contains(solicitud1));
        assertTrue(asignatura.getSolicitudes().contains(solicitud2));

        asignatura.getSolicitudes().remove(solicitud1);
        assertEquals(1, asignatura.getSolicitudes().size());
        assertFalse(asignatura.getSolicitudes().contains(solicitud1));
        assertTrue(asignatura.getSolicitudes().contains(solicitud2));
    }

    @Test
    void testAsignaturaEqualsAndHashCode() {
        Asignatura asignatura1 = Asignatura.builder()
                .id(1L)
                .name("Química")
                .code("QUI301")
                .build();

        Asignatura asignatura2 = Asignatura.builder()
                .id(1L)
                .name("Química")
                .code("QUI301")
                .build();

        Asignatura asignatura3 = Asignatura.builder()
                .id(2L)
                .name("Biología")
                .code("BIO401")
                .build();

        assertEquals(asignatura1, asignatura2);
        assertEquals(asignatura1.hashCode(), asignatura2.hashCode());
        assertNotEquals(asignatura1, asignatura3);
        assertNotEquals(asignatura1.hashCode(), asignatura3.hashCode());
    }

    @Test
    void testAsignaturaWithSolicitudes() {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setName("Programación");
        asignatura.setCode("PRG101");

        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);
        
        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);

        List<Solicitud> solicitudes = Arrays.asList(solicitud1, solicitud2);
        asignatura.setSolicitudes(solicitudes);

        assertEquals(2, asignatura.getSolicitudes().size());
        assertTrue(asignatura.getSolicitudes().contains(solicitud1));
        assertTrue(asignatura.getSolicitudes().contains(solicitud2));
    }

    @Test
    void testAsignaturaTimestamps() {
        Asignatura asignatura = new Asignatura();
        LocalDateTime now = LocalDateTime.now();
        
        asignatura.setCreatedAt(now);
        asignatura.setLastModifiedDate(now.plusHours(1));

        assertEquals(now, asignatura.getCreatedAt());
        assertTrue(asignatura.getLastModifiedDate().isAfter(asignatura.getCreatedAt()));
    }

    @Test
    void testAsignaturaToString() {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setName("Matemáticas");
        asignatura.setCode("MAT101");

        String toString = asignatura.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name='Matemáticas'"));
        assertTrue(toString.contains("code='MAT101'"));
        assertEquals("Asignatura{id=1, name='Matemáticas', code='MAT101'}", toString);
    }

    @Test
    void testAsignaturaWithNullValues() {
        Asignatura asignatura = new Asignatura();
        
        assertNull(asignatura.getId());
        assertNull(asignatura.getName());
        assertNull(asignatura.getCode());
        assertNotNull(asignatura.getSolicitudes());
        assertTrue(asignatura.getSolicitudes().isEmpty());
    }
} 