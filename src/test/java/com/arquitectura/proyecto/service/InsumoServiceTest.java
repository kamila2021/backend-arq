package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.InsumoInput;
import com.arquitectura.proyecto.model.Insumo;
import com.arquitectura.proyecto.repository.InsumoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsumoServiceTest {

    @Mock
    private InsumoRepository insumoRepository;

    @InjectMocks
    private InsumoService insumoService;

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
    @WithMockUser
    void listarInsumos_Success() {
        List<Insumo> insumos = Arrays.asList(insumo);
        when(insumoRepository.findAll()).thenReturn(insumos);

        List<Insumo> result = insumoService.listarInsumos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(insumo.getNombre(), result.get(0).getNombre());
        verify(insumoRepository, times(1)).findAll();
    }

    @Test
    @WithMockUser
    void stockInsumosDisponibles_Success() {
        List<Insumo> insumos = Arrays.asList(insumo);
        when(insumoRepository.findByStockDisponibleGreaterThan(0)).thenReturn(insumos);

        List<Insumo> result = insumoService.sotckInsumosDisponibles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(insumo.getNombre(), result.get(0).getNombre());
        verify(insumoRepository, times(1)).findByStockDisponibleGreaterThan(0);
    }

    @Test
    @WithMockUser(roles = "Admin")
    void crearInsumo_Success() {
        when(insumoRepository.save(any(Insumo.class))).thenReturn(insumo);

        Insumo result = insumoService.crearInsumo(insumoInput);

        assertNotNull(result);
        assertEquals(insumo.getNombre(), result.getNombre());
        assertEquals(insumo.getTipo(), result.getTipo());
        verify(insumoRepository, times(1)).save(any(Insumo.class));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void modificarInsumo_Success() {
        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoRepository.save(any(Insumo.class))).thenReturn(insumo);

        Insumo result = insumoService.modificarInsumo(1L, insumoInput);

        assertNotNull(result);
        assertEquals(insumo.getNombre(), result.getNombre());
        verify(insumoRepository, times(1)).findById(1L);
        verify(insumoRepository, times(1)).save(any(Insumo.class));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void eliminarInsumo_Success() {
        doNothing().when(insumoRepository).deleteById(1L);

        insumoService.eliminarInsumo(1L);

        verify(insumoRepository, times(1)).deleteById(1L);
    }
} 