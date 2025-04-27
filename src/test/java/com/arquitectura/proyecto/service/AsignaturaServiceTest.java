package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.AsignaturaDto;
import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.repository.AsignaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaService asignaturaService;

    private Asignatura asignatura;
    private AsignaturaDto asignaturaDto;

    @BeforeEach
    void setUp() {
        asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Asignatura 1");
        asignatura.setCodigo("ASG001");

        asignaturaDto = new AsignaturaDto();
        asignaturaDto.setNombre("Asignatura 1");
        asignaturaDto.setCodigo("ASG001");
    }

    @Test
    @WithMockUser(roles = "Admin")
    void crearAsignatura_Success() {
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

        Asignatura result = asignaturaService.crearAsignatura(asignaturaDto);

        assertNotNull(result);
        assertEquals(asignatura.getNombre(), result.getNombre());
        assertEquals(asignatura.getCodigo(), result.getCodigo());
        verify(asignaturaRepository, times(1)).save(any(Asignatura.class));
    }

    @Test
    @WithMockUser
    void listarAsignaturas_Success() {
        List<Asignatura> asignaturas = Arrays.asList(asignatura);
        when(asignaturaRepository.findAll()).thenReturn(asignaturas);

        List<Asignatura> result = asignaturaService.listarAsignaturas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(asignatura.getNombre(), result.get(0).getNombre());
        verify(asignaturaRepository, times(1)).findAll();
    }
} 