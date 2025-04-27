package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.dto.AsignaturaDto;
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
        asignatura.setName("Asignatura 1");
        asignatura.setCode("ASG001");
    }

    @Test
    void createSubject_ShouldReturnAsignatura() {
        // Given
        String nombre = "Test Subject";
        String codigo = "TS101";
        AsignaturaDto dto = new AsignaturaDto();
        dto.setName(nombre);
        dto.setCode(codigo);
        
        when(asignaturaService.createSubject(any(AsignaturaDto.class))).thenReturn(new Asignatura());
        
        // When
        Asignatura result = asignaturaResolver.createSubject(nombre, codigo);
        
        // Then
        assertNotNull(result);
        verify(asignaturaService).createSubject(any(AsignaturaDto.class));
    }

    @Test
    void listarAsignaturas_Success() {
        List<Asignatura> asignaturas = Arrays.asList(asignatura);
        when(asignaturaService.listarAsignaturas()).thenReturn(asignaturas);

        List<Asignatura> result = asignaturaResolver.listarAsignaturas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(asignatura.getName(), result.get(0).getName());
        verify(asignaturaService, times(1)).listarAsignaturas();
    }
} 