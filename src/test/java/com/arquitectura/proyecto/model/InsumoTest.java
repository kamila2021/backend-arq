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
        solicitud1.setCantidad(5.0);

        SolicitudInsumo solicitud2 = new SolicitudInsumo();
        solicitud2.setId(2L);
        solicitud2.setCantidad(3.0);

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
        assertNull(insumo.getSolicitudes());
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
        solicitud1.setCantidad(5.0);

        SolicitudInsumo solicitud2 = new SolicitudInsumo();
        solicitud2.setId(2L);
        solicitud2.setInsumo(insumo);
        solicitud2.setCantidad(3.0);

        List<SolicitudInsumo> solicitudes = Arrays.asList(solicitud1, solicitud2);
        insumo.setSolicitudes(solicitudes);

        // Verificar relaciones bidireccionales
        assertEquals(2, insumo.getSolicitudes().size());
        assertEquals(insumo, solicitud1.getInsumo());
        assertEquals(insumo, solicitud2.getInsumo());
    }
} 