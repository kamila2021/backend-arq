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
        asignatura.setName("Matemáticas");
        asignatura.setCode("MAT101");

        asignaturaDto = new AsignaturaDto();
        asignaturaDto.setName("Matemáticas");
        asignaturaDto.setCode("MAT101");
    }

    @Test
    @WithMockUser(roles = "Admin")
    void createSubject_Success() {
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

        Asignatura result = asignaturaService.createSubject(asignaturaDto);

        assertNotNull(result);
        assertEquals(asignatura.getName(), result.getName());
        assertEquals(asignatura.getCode(), result.getCode());
        verify(asignaturaRepository).save(any(Asignatura.class));
    }

    @Test
    @WithMockUser
    void listarAsignaturas_Success() {
        List<Asignatura> asignaturas = Arrays.asList(asignatura);
        when(asignaturaRepository.findAll()).thenReturn(asignaturas);

        List<Asignatura> result = asignaturaService.listarAsignaturas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(asignatura.getName(), result.get(0).getName());
        verify(asignaturaRepository).findAll();
    }
} 