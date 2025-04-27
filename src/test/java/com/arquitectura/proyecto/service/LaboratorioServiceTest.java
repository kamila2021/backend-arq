package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.LaboratorioDto;
import com.arquitectura.proyecto.model.Laboratorio;
import com.arquitectura.proyecto.repository.LaboratorioRepository;
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
class LaboratorioServiceTest {

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private LaboratorioService laboratorioService;

    private Laboratorio laboratorio;
    private LaboratorioDto laboratorioDto;

    @BeforeEach
    void setUp() {
        laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Laboratorio Test");

        laboratorioDto = new LaboratorioDto();
        laboratorioDto.setNombre("Laboratorio 1");
        laboratorioDto.setCodigo("LAB001");
    }

    @Test
    @WithMockUser(roles = "Admin")
    void crearLaboratorio_Success() {
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);

        Laboratorio result = laboratorioService.crearLaboratorio(laboratorioDto);

        assertNotNull(result);
        assertEquals(laboratorio.getNombre(), result.getNombre());
        verify(laboratorioRepository, times(1)).save(any(Laboratorio.class));
    }

    @Test
    @WithMockUser
    void listarLaboratorios_Success() {
        List<Laboratorio> laboratorios = Arrays.asList(laboratorio);
        when(laboratorioRepository.findAll()).thenReturn(laboratorios);

        List<Laboratorio> result = laboratorioService.listarLaboratorios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(laboratorio.getNombre(), result.get(0).getNombre());
        verify(laboratorioRepository, times(1)).findAll();
    }

} 