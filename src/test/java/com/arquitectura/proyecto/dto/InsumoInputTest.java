package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsumoInputTest {

    @Test
    void testInsumoInput() {
        // Crear y configurar InsumoInput
        InsumoInput input = new InsumoInput();
        input.setNombre("Microscopio");
        input.setTipo("Equipo");
        input.setUnidadMedida("Unidad");
        input.setStockDisponible(10);

        // Verificar los valores
        assertEquals("Microscopio", input.getNombre());
        assertEquals("Equipo", input.getTipo());
        assertEquals("Unidad", input.getUnidadMedida());
        assertEquals(10, input.getStockDisponible());

        // Verificar toString
        assertNotNull(input.toString());

        // Probar constructor con todos los campos
        InsumoInput input2 = new InsumoInput();
        input2.setNombre("Probeta");
        input2.setTipo("Material");
        input2.setUnidadMedida("Unidad");
        input2.setStockDisponible(20);

        // Verificar equals y hashCode
        assertNotEquals(input, input2);
        InsumoInput input3 = new InsumoInput();
        input3.setNombre("Microscopio");
        input3.setTipo("Equipo");
        input3.setUnidadMedida("Unidad");
        input3.setStockDisponible(10);
        assertEquals(input, input3);
        assertEquals(input.hashCode(), input3.hashCode());
    }

    @Test
    void testInsumoInputWithNullValues() {
        InsumoInput input = new InsumoInput();
        
        assertNull(input.getNombre());
        assertNull(input.getTipo());
        assertNull(input.getUnidadMedida());
        assertNull(input.getStockDisponible());
    }
} 