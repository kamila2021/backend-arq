package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudDtoTest {

    @Test
    void testGettersAndSetters() {
        AsignaturaDto asignatura = new AsignaturaDto();
        asignatura.setId(1L);
        asignatura.setName("Matemáticas");
        asignatura.setCode("MAT101");

        SolicitudDto solicitud = new SolicitudDto();
        solicitud.setId(1L);
        solicitud.setAsignatura(asignatura);
        solicitud.setFechaUso("2024-04-27");
        solicitud.setHorario("10:00");
        solicitud.setCantGrupos(3);
        solicitud.setEstado(true);

        assertEquals(1L, solicitud.getId());
        assertEquals(asignatura, solicitud.getAsignatura());
        assertEquals("2024-04-27", solicitud.getFechaUso());
        assertEquals("10:00", solicitud.getHorario());
        assertEquals(3, solicitud.getCantGrupos());
        assertTrue(solicitud.getEstado());
    }

    @Test
    void testEqualsAndHashCode() {
        SolicitudDto solicitud1 = new SolicitudDto();
        solicitud1.setId(1L);
        solicitud1.setFechaUso("2024-04-27");
        solicitud1.setHorario("10:00");

        SolicitudDto solicitud2 = new SolicitudDto();
        solicitud2.setId(1L);
        solicitud2.setFechaUso("2024-04-27");
        solicitud2.setHorario("10:00");

        assertEquals(solicitud1, solicitud2);
        assertEquals(solicitud1.hashCode(), solicitud2.hashCode());

        solicitud2.setId(2L);
        assertNotEquals(solicitud1, solicitud2);
        assertNotEquals(solicitud1.hashCode(), solicitud2.hashCode());
    }

    @Test
    void testToString() {
        SolicitudDto solicitud = new SolicitudDto();
        solicitud.setId(1L);
        solicitud.setFechaUso("2024-04-27");
        solicitud.setHorario("10:00");

        String toString = solicitud.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("fechaUso=2024-04-27"));
        assertTrue(toString.contains("horario=10:00"));
    }

    @Test
    void testSolicitudDtoWithNullValues() {
        SolicitudDto solicitud = new SolicitudDto();
        
        assertNull(solicitud.getId());
        assertNull(solicitud.getFechaSolicitud());
        assertNull(solicitud.getFechaUso());
        assertNull(solicitud.getHorario());
        assertNull(solicitud.getCantGrupos());
        assertNull(solicitud.getEstado());
        assertNull(solicitud.getUsuario());
        assertNull(solicitud.getAsignatura());
        assertNull(solicitud.getLaboratorio());
        assertNull(solicitud.getInsumos());
    }
} 