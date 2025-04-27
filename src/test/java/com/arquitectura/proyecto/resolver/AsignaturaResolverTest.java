package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.service.AsignaturaService;
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
class AsignaturaResolverTest {

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AsignaturaResolver asignaturaResolver;

    private Asignatura asignatura;

    @BeforeEach
    void setUp() {
        asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Asignatura 1");
        asignatura.setCodigo("ASG001");
    }

    @Test
    void crearAsignatura_Success() {
        when(asignaturaService.crearAsignatura(any())).thenReturn(asignatura);

        Asignatura result = asignaturaResolver.crearAsignatura("Asignatura 1", "ASG001");

        assertNotNull(result);
        assertEquals(asignatura.getNombre(), result.getNombre());
        assertEquals(asignatura.getCodigo(), result.getCodigo());
        verify(asignaturaService, times(1)).crearAsignatura(any());
    }

    @Test
    void listarAsignaturas_Success() {
        List<Asignatura> asignaturas = Arrays.asList(asignatura);
        when(asignaturaService.listarAsignaturas()).thenReturn(asignaturas);

        List<Asignatura> result = asignaturaResolver.listarAsignaturas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(asignatura.getNombre(), result.get(0).getNombre());
        verify(asignaturaService, times(1)).listarAsignaturas();
    }
} 