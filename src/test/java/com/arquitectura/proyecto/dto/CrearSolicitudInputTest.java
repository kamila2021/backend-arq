package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CrearSolicitudInputTest {

    @Test
    void testCrearSolicitudInput() {
        // Crear lista de insumos
        InsumoCantidadInput insumo1 = new InsumoCantidadInput();
        insumo1.setIdInsumo(1L);
        insumo1.setCantidad(5.0);

        InsumoCantidadInput insumo2 = new InsumoCantidadInput();
        insumo2.setIdInsumo(2L);
        insumo2.setCantidad(3.0);

        List<InsumoCantidadInput> insumos = Arrays.asList(insumo1, insumo2);

        // Crear y configurar CrearSolicitudInput
        CrearSolicitudInput input = new CrearSolicitudInput();
        input.setIdUsuario(1L);
        input.setIdAsignatura(2L);
        input.setIdLaboratorio(3L);
        input.setFechaUso("2024-04-27");
        input.setHorario("10:00");
        input.setCantGrupos(2);
        input.setInsumos(insumos);

        // Verificar los valores
        assertEquals(1L, input.getIdUsuario());
        assertEquals(2L, input.getIdAsignatura());
        assertEquals(3L, input.getIdLaboratorio());
        assertEquals("2024-04-27", input.getFechaUso());
        assertEquals("10:00", input.getHorario());
        assertEquals(2, input.getCantGrupos());
        assertEquals(insumos, input.getInsumos());

        // Verificar toString
        assertNotNull(input.toString());

        // Probar constructor con todos los campos
        CrearSolicitudInput input2 = new CrearSolicitudInput();
        input2.setIdUsuario(4L);
        input2.setIdAsignatura(5L);
        input2.setIdLaboratorio(6L);
        input2.setFechaUso("2024-04-28");
        input2.setHorario("11:00");
        input2.setCantGrupos(3);
        input2.setInsumos(new ArrayList<>());

        // Verificar equals y hashCode
        assertNotEquals(input, input2);
        CrearSolicitudInput input3 = new CrearSolicitudInput();
        input3.setIdUsuario(1L);
        input3.setIdAsignatura(2L);
        input3.setIdLaboratorio(3L);
        input3.setFechaUso("2024-04-27");
        input3.setHorario("10:00");
        input3.setCantGrupos(2);
        input3.setInsumos(insumos);
        assertEquals(input, input3);
        assertEquals(input.hashCode(), input3.hashCode());
    }

    @Test
    void testCrearSolicitudInputWithNullValues() {
        CrearSolicitudInput input = new CrearSolicitudInput();
        
        assertNull(input.getIdUsuario());
        assertNull(input.getIdAsignatura());
        assertNull(input.getIdLaboratorio());
        assertNull(input.getFechaUso());
        assertNull(input.getHorario());
        assertEquals(0, input.getCantGrupos());
        assertNull(input.getInsumos());
    }
} 