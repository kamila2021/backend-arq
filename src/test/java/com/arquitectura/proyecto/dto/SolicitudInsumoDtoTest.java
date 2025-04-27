package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolicitudInsumoDtoTest {

    @Test
    void testSolicitudInsumoDtoSettersAndGetters() {
        InsumoDto insumo = new InsumoDto();
        insumo.setId(1L);
        insumo.setNombre("Microscopio");
        insumo.setTipo("Equipo");
        
        SolicitudInsumoDto solicitudInsumo = new SolicitudInsumoDto();
        solicitudInsumo.setId(1L);
        solicitudInsumo.setInsumo(insumo);
        solicitudInsumo.setCantidad(2.5);

        assertEquals(1L, solicitudInsumo.getId());
        assertEquals(insumo, solicitudInsumo.getInsumo());
        assertEquals(2.5, solicitudInsumo.getCantidad());
    }

    @Test
    void testSolicitudInsumoDtoEqualsAndHashCode() {
        InsumoDto insumo1 = new InsumoDto();
        insumo1.setId(1L);
        insumo1.setNombre("Microscopio");

        SolicitudInsumoDto solicitud1 = new SolicitudInsumoDto();
        solicitud1.setId(1L);
        solicitud1.setInsumo(insumo1);
        solicitud1.setCantidad(2.5);

        SolicitudInsumoDto solicitud2 = new SolicitudInsumoDto();
        solicitud2.setId(1L);
        solicitud2.setInsumo(insumo1);
        solicitud2.setCantidad(2.5);

        InsumoDto insumo2 = new InsumoDto();
        insumo2.setId(2L);
        insumo2.setNombre("Probeta");

        SolicitudInsumoDto solicitud3 = new SolicitudInsumoDto();
        solicitud3.setId(2L);
        solicitud3.setInsumo(insumo2);
        solicitud3.setCantidad(3.0);

        assertEquals(solicitud1, solicitud2);
        assertEquals(solicitud1.hashCode(), solicitud2.hashCode());
        assertNotEquals(solicitud1, solicitud3);
        assertNotEquals(solicitud1.hashCode(), solicitud3.hashCode());
    }

    @Test
    void testSolicitudInsumoDtoToString() {
        InsumoDto insumo = new InsumoDto();
        insumo.setId(1L);
        insumo.setNombre("Microscopio");

        SolicitudInsumoDto solicitudInsumo = new SolicitudInsumoDto();
        solicitudInsumo.setId(1L);
        solicitudInsumo.setInsumo(insumo);
        solicitudInsumo.setCantidad(2.5);

        String toString = solicitudInsumo.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("cantidad=2.5"));
        assertTrue(toString.contains("insumo"));
    }

    @Test
    void testSolicitudInsumoDtoWithNullValues() {
        SolicitudInsumoDto solicitudInsumo = new SolicitudInsumoDto();
        
        assertNull(solicitudInsumo.getId());
        assertNull(solicitudInsumo.getInsumo());
        assertNull(solicitudInsumo.getCantidad());
    }

    @Test
    void testSolicitudInsumoDtoWithZeroCantidad() {
        SolicitudInsumoDto solicitudInsumo = new SolicitudInsumoDto();
        solicitudInsumo.setCantidad(0.0);
        
        assertEquals(0.0, solicitudInsumo.getCantidad());
    }

    @Test
    void testSolicitudInsumoDtoWithNegativeCantidad() {
        SolicitudInsumoDto solicitudInsumo = new SolicitudInsumoDto();
        solicitudInsumo.setCantidad(-1.5);
        
        assertEquals(-1.5, solicitudInsumo.getCantidad());
    }
} 