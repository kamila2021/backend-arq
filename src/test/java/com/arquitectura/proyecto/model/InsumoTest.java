package com.arquitectura.proyecto.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InsumoTest {

    @Test
    void testInsumo() {
        // Crear solicitudes de insumo
        SolicitudInsumo solicitud1 = new SolicitudInsumo();
        solicitud1.setId(1L);
        solicitud1.setCantidad(5.0f);

        SolicitudInsumo solicitud2 = new SolicitudInsumo();
        solicitud2.setId(2L);
        solicitud2.setCantidad(3.0f);

        List<SolicitudInsumo> solicitudes = Arrays.asList(solicitud1, solicitud2);

        // Crear y configurar Insumo
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNombre("Microscopio");
        insumo.setTipo("Equipo");
        insumo.setUnidadMedida("Unidad");
        insumo.setStockDisponible(10);
        insumo.setSolicitudes(solicitudes);

        // Verificar los valores
        assertEquals(1L, insumo.getId());
        assertEquals("Microscopio", insumo.getNombre());
        assertEquals("Equipo", insumo.getTipo());
        assertEquals("Unidad", insumo.getUnidadMedida());
        assertEquals(10, insumo.getStockDisponible());
        assertEquals(solicitudes, insumo.getSolicitudes());

        // Verificar toString
        assertNotNull(insumo.toString());

        // Probar constructor con todos los campos
        Insumo insumo2 = new Insumo();
        insumo2.setId(2L);
        insumo2.setNombre("Probeta");
        insumo2.setTipo("Material");
        insumo2.setUnidadMedida("Unidad");
        insumo2.setStockDisponible(20);
        insumo2.setSolicitudes(new ArrayList<>());

        // Verificar equals y hashCode
        assertNotEquals(insumo, insumo2);
        Insumo insumo3 = new Insumo();
        insumo3.setId(1L);
        insumo3.setNombre("Microscopio");
        insumo3.setTipo("Equipo");
        insumo3.setUnidadMedida("Unidad");
        insumo3.setStockDisponible(10);
        insumo3.setSolicitudes(solicitudes);
        assertEquals(insumo, insumo3);
        assertEquals(insumo.hashCode(), insumo3.hashCode());
    }

    @Test
    void testInsumoWithNullValues() {
        Insumo insumo = new Insumo();
        
        assertNull(insumo.getId());
        assertNull(insumo.getNombre());
        assertNull(insumo.getTipo());
        assertNull(insumo.getUnidadMedida());
        assertNull(insumo.getStockDisponible());
        assertNotNull(insumo.getSolicitudes(), "Solicitudes list should be initialized as empty list");
        assertTrue(insumo.getSolicitudes().isEmpty(), "Solicitudes list should be empty");
    }

    @Test
    void testInsumoRelationships() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNombre("Microscopio");

        // Crear y asociar solicitudes
        SolicitudInsumo solicitud1 = new SolicitudInsumo();
        solicitud1.setId(1L);
        solicitud1.setInsumo(insumo);
        solicitud1.setCantidad(5.0f);

        SolicitudInsumo solicitud2 = new SolicitudInsumo();
        solicitud2.setId(2L);
        solicitud2.setInsumo(insumo);
        solicitud2.setCantidad(3.0f);

        List<SolicitudInsumo> solicitudes = Arrays.asList(solicitud1, solicitud2);
        insumo.setSolicitudes(solicitudes);

        // Verificar relaciones bidireccionales
        assertEquals(2, insumo.getSolicitudes().size());
        assertEquals(insumo, solicitud1.getInsumo());
        assertEquals(insumo, solicitud2.getInsumo());
    }

    @Test
    void testEqualsAndHashCode() {
        Insumo insumo1 = Insumo.builder()
                .id(1L)
                .nombre("Microscopio")
                .tipo("Equipo")
                .unidadMedida("Unidad")
                .stockDisponible(10)
                .build();

        Insumo insumo2 = Insumo.builder()
                .id(1L)
                .nombre("Microscopio")
                .tipo("Equipo")
                .unidadMedida("Unidad")
                .stockDisponible(10)
                .build();

        Insumo insumo3 = Insumo.builder()
                .id(2L)
                .nombre("Probeta")
                .tipo("Material")
                .unidadMedida("Unidad")
                .stockDisponible(20)
                .build();

        // Test equals
        assertEquals(insumo1, insumo2);
        assertNotEquals(insumo1, insumo3);
        assertNotEquals(insumo1, null);
        assertNotEquals(insumo1, new Object());

        // Test hashCode
        assertEquals(insumo1.hashCode(), insumo2.hashCode());
        assertNotEquals(insumo1.hashCode(), insumo3.hashCode());
    }

    @Test
    void testToString() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNombre("Microscopio");
        insumo.setTipo("Equipo");
        insumo.setUnidadMedida("Unidad");
        insumo.setStockDisponible(10);

        String toString = insumo.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("nombre='Microscopio'"));
        assertTrue(toString.contains("tipo='Equipo'"));
        assertTrue(toString.contains("unidadMedida='Unidad'"));
        assertTrue(toString.contains("stockDisponible=10"));
        assertEquals("Insumo{id=1, nombre='Microscopio', tipo='Equipo', unidadMedida='Unidad', stockDisponible=10}", toString);
    }

    @Test
    void testBuilder() {
        List<SolicitudInsumo> solicitudes = new ArrayList<>();
        SolicitudInsumo solicitud = new SolicitudInsumo();
        solicitud.setId(1L);
        solicitud.setCantidad(5.0f);
        solicitudes.add(solicitud);

        Insumo insumo = Insumo.builder()
                .id(1L)
                .nombre("Reactivo A")
                .tipo("Químico")
                .unidadMedida("ml")
                .stockDisponible(100)
                .solicitudes(solicitudes)
                .build();

        assertEquals(1L, insumo.getId());
        assertEquals("Reactivo A", insumo.getNombre());
        assertEquals("Químico", insumo.getTipo());
        assertEquals("ml", insumo.getUnidadMedida());
        assertEquals(100, insumo.getStockDisponible());
        assertEquals(solicitudes, insumo.getSolicitudes());
    }

    @Test
    void testGettersAndSetters() {
        Insumo insumo = new Insumo();

        insumo.setId(1L);
        insumo.setNombre("Reactivo B");
        insumo.setTipo("Químico");
        insumo.setUnidadMedida("g");
        insumo.setStockDisponible(50);

        List<SolicitudInsumo> solicitudes = new ArrayList<>();
        SolicitudInsumo solicitud = new SolicitudInsumo();
        solicitud.setId(1L);
        solicitud.setCantidad(3.0f);
        solicitudes.add(solicitud);
        insumo.setSolicitudes(solicitudes);

        assertEquals(1L, insumo.getId());
        assertEquals("Reactivo B", insumo.getNombre());
        assertEquals("Químico", insumo.getTipo());
        assertEquals("g", insumo.getUnidadMedida());
        assertEquals(50, insumo.getStockDisponible());
        assertEquals(solicitudes, insumo.getSolicitudes());
    }

    @Test
    void testSolicitudesListManagement() {
        Insumo insumo = new Insumo();
        assertNotNull(insumo.getSolicitudes(), "Solicitudes list should be initialized");

        SolicitudInsumo solicitud1 = new SolicitudInsumo();
        solicitud1.setId(1L);
        solicitud1.setCantidad(3.0f);
        solicitud1.setInsumo(insumo);

        SolicitudInsumo solicitud2 = new SolicitudInsumo();
        solicitud2.setId(2L);
        solicitud2.setCantidad(4.0f);
        solicitud2.setInsumo(insumo);

        insumo.getSolicitudes().add(solicitud1);
        insumo.getSolicitudes().add(solicitud2);

        assertEquals(2, insumo.getSolicitudes().size());
        assertTrue(insumo.getSolicitudes().contains(solicitud1));
        assertTrue(insumo.getSolicitudes().contains(solicitud2));

        // Test bidirectional relationship
        assertEquals(insumo, solicitud1.getInsumo());
        assertEquals(insumo, solicitud2.getInsumo());

        insumo.getSolicitudes().remove(solicitud1);
        assertEquals(1, insumo.getSolicitudes().size());
        assertFalse(insumo.getSolicitudes().contains(solicitud1));
        assertTrue(insumo.getSolicitudes().contains(solicitud2));
    }
} 