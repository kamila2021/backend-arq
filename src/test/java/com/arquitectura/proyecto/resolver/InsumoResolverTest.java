package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.dto.InsumoInput;
import com.arquitectura.proyecto.model.Insumo;
import com.arquitectura.proyecto.service.InsumoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsumoResolverTest {

    @Mock
    private InsumoService insumoService;

    @InjectMocks
    private InsumoResolver insumoResolver;

    private Insumo insumo;
    private InsumoInput insumoInput;

    @BeforeEach
    void setUp() {
        insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNombre("Insumo 1");
        insumo.setTipo("Tipo 1");
        insumo.setUnidadMedida("Unidad 1");
        insumo.setStockDisponible(10);

        insumoInput = new InsumoInput();
        insumoInput.setNombre("Insumo 1");
        insumoInput.setTipo("Tipo 1");
        insumoInput.setUnidadMedida("Unidad 1");
        insumoInput.setStockDisponible(10);
    }

    @Test
    void listarInsumos_Success() {
        List<Insumo> insumos = Arrays.asList(insumo);
        when(insumoService.listarInsumos()).thenReturn(insumos);

        List<Insumo> result = insumoResolver.listarInsumos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(insumo.getNombre(), result.get(0).getNombre());
        verify(insumoService, times(1)).listarInsumos();
    }

    @Test
    void listarInsumos_EmptyList() {
        when(insumoService.listarInsumos()).thenReturn(Collections.emptyList());

        List<Insumo> result = insumoResolver.listarInsumos();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(insumoService, times(1)).listarInsumos();
    }

    @Test
    void stockInsumosDisponibles_Success() {
        List<Insumo> insumos = Arrays.asList(insumo);
        when(insumoService.sotckInsumosDisponibles()).thenReturn(insumos);

        List<Insumo> result = insumoResolver.stockInsumosDisponibles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(insumo.getNombre(), result.get(0).getNombre());
        verify(insumoService, times(1)).sotckInsumosDisponibles();
    }

    @Test
    void stockInsumosDisponibles_EmptyList() {
        when(insumoService.sotckInsumosDisponibles()).thenReturn(Collections.emptyList());

        List<Insumo> result = insumoResolver.stockInsumosDisponibles();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(insumoService, times(1)).sotckInsumosDisponibles();
    }

    @Test
    void crearInsumo_Success() {
        when(insumoService.crearInsumo(any())).thenReturn(insumo);

        Insumo result = insumoResolver.crearInsumo(insumoInput);

        assertNotNull(result);
        assertEquals(insumo.getNombre(), result.getNombre());
        verify(insumoService, times(1)).crearInsumo(any());
    }

    @Test
    void crearInsumo_InvalidInput() {
        InsumoInput invalidInput = new InsumoInput();
        when(insumoService.crearInsumo(any())).thenThrow(new IllegalArgumentException("Invalid input"));

        assertThrows(IllegalArgumentException.class, () -> {
            insumoResolver.crearInsumo(invalidInput);
        });
        verify(insumoService, times(1)).crearInsumo(any());
    }

    @Test
    void crearInsumo_AccessDenied() {
        when(insumoService.crearInsumo(any())).thenThrow(new AccessDeniedException("Access denied"));

        assertThrows(AccessDeniedException.class, () -> {
            insumoResolver.crearInsumo(insumoInput);
        });
        verify(insumoService, times(1)).crearInsumo(any());
    }

    @Test
    void modificarInsumo_Success() {
        when(insumoService.modificarInsumo(anyLong(), any())).thenReturn(insumo);

        Insumo result = insumoResolver.modificarInsumo(1L, insumoInput);

        assertNotNull(result);
        assertEquals(insumo.getNombre(), result.getNombre());
        verify(insumoService, times(1)).modificarInsumo(anyLong(), any());
    }

    @Test
    void modificarInsumo_NotFound() {
        when(insumoService.modificarInsumo(anyLong(), any())).thenThrow(new IllegalArgumentException("Insumo not found"));

        assertThrows(IllegalArgumentException.class, () -> {
            insumoResolver.modificarInsumo(1L, insumoInput);
        });
        verify(insumoService, times(1)).modificarInsumo(anyLong(), any());
    }

    @Test
    void modificarInsumo_InvalidInput() {
        InsumoInput invalidInput = new InsumoInput();
        when(insumoService.modificarInsumo(anyLong(), any())).thenThrow(new IllegalArgumentException("Invalid input"));

        assertThrows(IllegalArgumentException.class, () -> {
            insumoResolver.modificarInsumo(1L, invalidInput);
        });
        verify(insumoService, times(1)).modificarInsumo(anyLong(), any());
    }

    @Test
    void eliminarInsumo_Success() {
        doNothing().when(insumoService).eliminarInsumo(anyLong());

        Boolean result = insumoResolver.eliminarInsumo(1L);

        assertTrue(result);
        verify(insumoService, times(1)).eliminarInsumo(anyLong());
    }

    @Test
    void eliminarInsumo_NotFound() {
        doThrow(new IllegalArgumentException("Insumo not found")).when(insumoService).eliminarInsumo(anyLong());

        assertThrows(IllegalArgumentException.class, () -> {
            insumoResolver.eliminarInsumo(1L);
        });
        verify(insumoService, times(1)).eliminarInsumo(anyLong());
    }

    @Test
    void eliminarInsumo_AccessDenied() {
        doThrow(new AccessDeniedException("Access denied")).when(insumoService).eliminarInsumo(anyLong());

        assertThrows(AccessDeniedException.class, () -> {
            insumoResolver.eliminarInsumo(1L);
        });
        verify(insumoService, times(1)).eliminarInsumo(anyLong());
    }
} 