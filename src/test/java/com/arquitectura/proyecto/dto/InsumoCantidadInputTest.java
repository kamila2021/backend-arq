package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsumoCantidadInputTest {

    @Test
    void testInsumoCantidadInput() {
        // Crear y configurar InsumoCantidadInput
        InsumoCantidadInput input = new InsumoCantidadInput();
        input.setIdInsumo(1L);
        input.setCantidad(5.0);

        // Verificar los valores
        assertEquals(1L, input.getIdInsumo());
        assertEquals(5.0, input.getCantidad());

        // Verificar toString
        assertNotNull(input.toString());

        // Probar constructor con todos los campos
        InsumoCantidadInput input2 = new InsumoCantidadInput();
        input2.setIdInsumo(2L);
        input2.setCantidad(3.0);

        // Verificar equals y hashCode
        assertNotEquals(input, input2);
        InsumoCantidadInput input3 = new InsumoCantidadInput();
        input3.setIdInsumo(1L);
        input3.setCantidad(5.0);
        assertEquals(input, input3);
        assertEquals(input.hashCode(), input3.hashCode());
    }

    @Test
    void testInsumoCantidadInputWithNullValues() {
        InsumoCantidadInput input = new InsumoCantidadInput();
        
        assertNull(input.getIdInsumo());
        assertNull(input.getCantidad());
    }
} 