package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolicitudTest {

    @Test
    void testBuilder() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setName("Matemáticas");

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        List<SolicitudInsumo> insumos = new ArrayList<>();
        SolicitudInsumo insumo = new SolicitudInsumo();
        insumo.setId(1L);
        insumo.setCantidad(5.0f);
        insumos.add(insumo);

        LocalDate fechaUso = LocalDate.now().plusDays(1);
        String horario = "10:00";

        Solicitud solicitud = Solicitud.builder()
                .id(1L)
                .usuario(usuario)
                .asignatura(asignatura)
                .laboratorio(laboratorio)
                .fechaSolicitud(LocalDate.now())
                .fechaUso(fechaUso)
                .horario(horario)
                .cantGrupos(2)
                .estado(true)
                .insumos(insumos)
                .build();

        assertEquals(1L, solicitud.getId());
        assertEquals(usuario, solicitud.getUsuario());
        assertEquals(asignatura, solicitud.getAsignatura());
        assertEquals(laboratorio, solicitud.getLaboratorio());
        assertEquals(fechaUso, solicitud.getFechaUso());
        assertEquals(horario, solicitud.getHorario());
        assertEquals(2, solicitud.getCantGrupos());
        assertTrue(solicitud.getEstado());
        assertEquals(insumos, solicitud.getInsumos());
    }

    @Test
    void testGettersAndSetters() {
        Solicitud solicitud = new Solicitud();

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setName("Física");

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        LocalDate fechaUso = LocalDate.now().plusDays(1);
        String horario = "14:00";

        solicitud.setId(1L);
        solicitud.setUsuario(usuario);
        solicitud.setAsignatura(asignatura);
        solicitud.setLaboratorio(laboratorio);
        solicitud.setFechaSolicitud(LocalDate.now());
        solicitud.setFechaUso(fechaUso);
        solicitud.setHorario(horario);
        solicitud.setCantGrupos(3);
        solicitud.setEstado(true);

        List<SolicitudInsumo> insumos = new ArrayList<>();
        SolicitudInsumo insumo = new SolicitudInsumo();
        insumo.setId(1L);
        insumo.setCantidad(5.0f);
        insumos.add(insumo);
        solicitud.setInsumos(insumos);

        assertEquals(1L, solicitud.getId());
        assertEquals(usuario, solicitud.getUsuario());
        assertEquals(asignatura, solicitud.getAsignatura());
        assertEquals(laboratorio, solicitud.getLaboratorio());
        assertEquals(fechaUso, solicitud.getFechaUso());
        assertEquals(horario, solicitud.getHorario());
        assertEquals(3, solicitud.getCantGrupos());
        assertTrue(solicitud.getEstado());
        assertEquals(insumos, solicitud.getInsumos());
    }

    @Test
    void testInsumosListManagement() {
        Solicitud solicitud = new Solicitud();
        assertNotNull(solicitud.getInsumos(), "Insumos list should be initialized");
        assertTrue(solicitud.getInsumos().isEmpty(), "Initial insumos list should be empty");

        SolicitudInsumo insumo1 = new SolicitudInsumo();
        insumo1.setId(1L);
        insumo1.setCantidad(3.0f);
        insumo1.setSolicitud(solicitud);

        SolicitudInsumo insumo2 = new SolicitudInsumo();
        insumo2.setId(2L);
        insumo2.setCantidad(4.0f);
        insumo2.setSolicitud(solicitud);

        solicitud.getInsumos().add(insumo1);
        solicitud.getInsumos().add(insumo2);

        assertEquals(2, solicitud.getInsumos().size());
        assertTrue(solicitud.getInsumos().contains(insumo1));
        assertTrue(solicitud.getInsumos().contains(insumo2));

        // Test bidirectional relationship
        assertEquals(solicitud, insumo1.getSolicitud());
        assertEquals(solicitud, insumo2.getSolicitud());

        solicitud.getInsumos().remove(insumo1);
        assertEquals(1, solicitud.getInsumos().size());
        assertFalse(solicitud.getInsumos().contains(insumo1));
        assertTrue(solicitud.getInsumos().contains(insumo2));
    }

    @Test
    void testSolicitud() {
        // Crear objetos necesarios
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setName("Química");

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Lab Química");

        LocalDate fechaSolicitud = LocalDate.now();
        LocalDate fechaUso = LocalDate.now().plusDays(1);
        String horario = "10:00";

        // Crear solicitudes de insumo
        SolicitudInsumo insumo1 = new SolicitudInsumo();
        insumo1.setId(1L);
        insumo1.setCantidad(5.0f);

        SolicitudInsumo insumo2 = new SolicitudInsumo();
        insumo2.setId(2L);
        insumo2.setCantidad(3.0f);

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
        solicitud2.setHorario("14:00");
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
        assertNotNull(solicitud.getInsumos(), "Insumos list should be initialized as empty list");
        assertTrue(solicitud.getInsumos().isEmpty());
    }

    @Test
    void testSolicitudRelationships() {
        Solicitud solicitud = new Solicitud();
        
        // Test Usuario relationship
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        solicitud.setUsuario(usuario);
        assertEquals(usuario, solicitud.getUsuario());
        
        // Test Asignatura relationship
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        solicitud.setAsignatura(asignatura);
        assertEquals(asignatura, solicitud.getAsignatura());
        
        // Test Laboratorio relationship
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        solicitud.setLaboratorio(laboratorio);
        assertEquals(laboratorio, solicitud.getLaboratorio());
        
        // Test SolicitudInsumo bidirectional relationship
        SolicitudInsumo insumo = new SolicitudInsumo();
        insumo.setId(1L);
        insumo.setCantidad(5.0f);
        insumo.setSolicitud(solicitud);
        solicitud.getInsumos().add(insumo);
        
        assertTrue(solicitud.getInsumos().contains(insumo));
        assertEquals(solicitud, insumo.getSolicitud());
    }

    @Test
    void testEqualsAndHashCode() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        LocalDate fechaUso = LocalDate.now().plusDays(1);
        
        Solicitud solicitud1 = Solicitud.builder()
                .id(1L)
                .usuario(usuario)
                .asignatura(asignatura)
                .laboratorio(laboratorio)
                .fechaSolicitud(LocalDate.now())
                .fechaUso(fechaUso)
                .horario("10:00")
                .cantGrupos(2)
                .estado(true)
                .build();

        Solicitud solicitud2 = Solicitud.builder()
                .id(1L)
                .usuario(usuario)
                .asignatura(asignatura)
                .laboratorio(laboratorio)
                .fechaSolicitud(LocalDate.now())
                .fechaUso(fechaUso)
                .horario("10:00")
                .cantGrupos(2)
                .estado(true)
                .build();

        Solicitud solicitud3 = Solicitud.builder()
                .id(2L)
                .usuario(new Usuario())
                .asignatura(new Asignatura())
                .laboratorio(new Laboratorio())
                .fechaSolicitud(LocalDate.now())
                .fechaUso(LocalDate.now().plusDays(2))
                .horario("14:00")
                .cantGrupos(3)
                .estado(false)
                .build();

        // Test equals
        assertEquals(solicitud1, solicitud2);
        assertNotEquals(solicitud1, solicitud3);
        assertNotEquals(solicitud1, null);
        assertNotEquals(solicitud1, new Object());

        // Test hashCode
        assertEquals(solicitud1.hashCode(), solicitud2.hashCode());
        assertNotEquals(solicitud1.hashCode(), solicitud3.hashCode());
    }

    @Test
    void testToString() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setFechaSolicitud(LocalDate.of(2024, 4, 27));
        solicitud.setFechaUso(LocalDate.of(2024, 4, 28));
        solicitud.setHorario("10:00");
        solicitud.setCantGrupos(2);
        solicitud.setEstado(true);

        String toString = solicitud.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("fechaSolicitud=2024-04-27"));
        assertTrue(toString.contains("fechaUso=2024-04-28"));
        assertTrue(toString.contains("horario='10:00'"));
        assertTrue(toString.contains("cantGrupos=2"));
        assertTrue(toString.contains("estado=true"));
        assertEquals("Solicitud{id=1, fechaSolicitud=2024-04-27, fechaUso=2024-04-28, horario='10:00', cantGrupos=2, estado=true}", toString);
    }
} 