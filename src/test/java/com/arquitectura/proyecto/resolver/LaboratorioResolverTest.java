package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.model.Laboratorio;
import com.arquitectura.proyecto.service.LaboratorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorioResolverTest {

    @Mock
    private LaboratorioService laboratorioService;

    @InjectMocks
    private LaboratorioResolver laboratorioResolver;

    private Laboratorio laboratorio;

    @BeforeEach
    void setUp() {
        laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Laboratorio Test");
    }

    @Test
    void listarLaboratorios_Success() {
        List<Laboratorio> laboratorios = Arrays.asList(laboratorio);
        when(laboratorioService.listarLaboratorios()).thenReturn(laboratorios);

        List<Laboratorio> result = laboratorioResolver.listarLaboratorios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(laboratorio.getNombre(), result.get(0).getNombre());
        verify(laboratorioService, times(1)).listarLaboratorios();
    }

    @Test
    void crearLaboratorio_Success() {
        when(laboratorioService.crearLaboratorio(any())).thenReturn(laboratorio);

        Laboratorio result = laboratorioResolver.crearLaboratorio("Laboratorio Test", "30");

        assertNotNull(result);
        assertEquals(laboratorio.getNombre(), result.getNombre());
        verify(laboratorioService, times(1)).crearLaboratorio(any());
    }

} 