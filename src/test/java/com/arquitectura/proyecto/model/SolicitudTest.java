package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolicitudTest {

    @Test
    void testSolicitud() {
        // Crear objetos necesarios
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Química");

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Lab Química");

        LocalDate fechaSolicitud = LocalDate.now();
        LocalDate fechaUso = LocalDate.now().plusDays(1);
        LocalTime horario = LocalTime.of(10, 0);

        // Crear solicitudes de insumo
        SolicitudInsumo insumo1 = new SolicitudInsumo();
        insumo1.setId(1L);
        insumo1.setCantidad(5.0);

        SolicitudInsumo insumo2 = new SolicitudInsumo();
        insumo2.setId(2L);
        insumo2.setCantidad(3.0);

        List<SolicitudInsumo> insumos = Arrays.asList(insumo1, insumo2);

        // Crear y configurar Solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setUsuario(usuario);
        solicitud.setAsignatura(asignatura);
        solicitud.setLaboratorio(laboratorio);
        solicitud.setFechaSolicitud(fechaSolicitud);
        solicitud.setFechaUso(fechaUso);
        solicitud.setHorario(horario);
        solicitud.setCantGrupos(2);
        solicitud.setEstado(true);
        solicitud.setInsumos(insumos);

        // Verificar los valores
        assertEquals(1L, solicitud.getId());
        assertEquals(usuario, solicitud.getUsuario());
        assertEquals(asignatura, solicitud.getAsignatura());
        assertEquals(laboratorio, solicitud.getLaboratorio());
        assertEquals(fechaSolicitud, solicitud.getFechaSolicitud());
        assertEquals(fechaUso, solicitud.getFechaUso());
        assertEquals(horario, solicitud.getHorario());
        assertEquals(2, solicitud.getCantGrupos());
        assertTrue(solicitud.getEstado());
        assertEquals(insumos, solicitud.getInsumos());

        // Verificar toString
        assertNotNull(solicitud.toString());

        // Probar constructor con todos los campos
        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);
        solicitud2.setUsuario(new Usuario());
        solicitud2.setAsignatura(new Asignatura());
        solicitud2.setLaboratorio(new Laboratorio());
        solicitud2.setFechaSolicitud(LocalDate.now());
        solicitud2.setFechaUso(LocalDate.now().plusDays(2));
        solicitud2.setHorario(LocalTime.of(14, 0));
        solicitud2.setCantGrupos(3);
        solicitud2.setEstado(false);
        solicitud2.setInsumos(new ArrayList<>());

        // Verificar equals y hashCode
        assertNotEquals(solicitud, solicitud2);
        Solicitud solicitud3 = new Solicitud();
        solicitud3.setId(1L);
        solicitud3.setUsuario(usuario);
        solicitud3.setAsignatura(asignatura);
        solicitud3.setLaboratorio(laboratorio);
        solicitud3.setFechaSolicitud(fechaSolicitud);
        solicitud3.setFechaUso(fechaUso);
        solicitud3.setHorario(horario);
        solicitud3.setCantGrupos(2);
        solicitud3.setEstado(true);
        solicitud3.setInsumos(insumos);
        assertEquals(solicitud, solicitud3);
        assertEquals(solicitud.hashCode(), solicitud3.hashCode());
    }

    @Test
    void testSolicitudWithNullValues() {
        Solicitud solicitud = new Solicitud();
        
        assertNull(solicitud.getId());
        assertNull(solicitud.getUsuario());
        assertNull(solicitud.getAsignatura());
        assertNull(solicitud.getLaboratorio());
        assertNull(solicitud.getFechaSolicitud());
        assertNull(solicitud.getFechaUso());
        assertNull(solicitud.getHorario());
        assertNull(solicitud.getCantGrupos());
        assertNull(solicitud.getEstado());
        assertNull(solicitud.getInsumos());
    }

    @Test
    void testSolicitudRelationships() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);

        // Probar relación con Usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        solicitud.setUsuario(usuario);
        assertEquals(usuario, solicitud.getUsuario());

        // Probar relación con Asignatura
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Química");
        solicitud.setAsignatura(asignatura);
        assertEquals(asignatura, solicitud.getAsignatura());

        // Probar relación con Laboratorio
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Lab Química");
        solicitud.setLaboratorio(laboratorio);
        assertEquals(laboratorio, solicitud.getLaboratorio());

        // Probar relación con SolicitudInsumo
        SolicitudInsumo insumo1 = new SolicitudInsumo();
        insumo1.setId(1L);
        insumo1.setSolicitud(solicitud);
        insumo1.setCantidad(5.0);

        SolicitudInsumo insumo2 = new SolicitudInsumo();
        insumo2.setId(2L);
        insumo2.setSolicitud(solicitud);
        insumo2.setCantidad(3.0);

        List<SolicitudInsumo> insumos = Arrays.asList(insumo1, insumo2);
        solicitud.setInsumos(insumos);

        assertEquals(2, solicitud.getInsumos().size());
        assertTrue(solicitud.getInsumos().contains(insumo1));
        assertTrue(solicitud.getInsumos().contains(insumo2));
    }
} 