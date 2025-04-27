package com.arquitectura.proyecto.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class SolicitudDtoTest {

    @Test
    void testSolicitudDto() {
        // Crear UsuarioDto
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);
        usuarioDto.setNombre("Usuario Test");
        usuarioDto.setEmail("test@example.com");

        // Crear AsignaturaDto
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setId(1L);
        asignaturaDto.setNombre("Asignatura Test");

        // Crear LaboratorioDto
        LaboratorioDto laboratorioDto = new LaboratorioDto();
        laboratorioDto.setId(1L);
        laboratorioDto.setNombre("Laboratorio Test");

        // Crear InsumoDto
        InsumoDto insumoDto = new InsumoDto();
        insumoDto.setId(1L);
        insumoDto.setNombre("Insumo Test");

        // Crear SolicitudInsumoDto
        SolicitudInsumoDto solicitudInsumoDto = new SolicitudInsumoDto();
        solicitudInsumoDto.setId(1L);
        solicitudInsumoDto.setInsumo(insumoDto);
        solicitudInsumoDto.setCantidad(5.0);

        List<SolicitudInsumoDto> insumos = Arrays.asList(solicitudInsumoDto);

        // Crear y configurar SolicitudDto
        SolicitudDto dto = new SolicitudDto();
        dto.setId(1L);
        dto.setFechaSolicitud("2024-04-27");
        dto.setFechaUso("2024-04-28");
        dto.setHorario("10:00");
        dto.setCantGrupos(2);
        dto.setEstado(true);
        dto.setUsuario(usuarioDto);
        dto.setAsignatura(asignaturaDto);
        dto.setLaboratorio(laboratorioDto);
        dto.setInsumos(insumos);

        // Verificar los valores
        assertEquals(1L, dto.getId());
        assertEquals("2024-04-27", dto.getFechaSolicitud());
        assertEquals("2024-04-28", dto.getFechaUso());
        assertEquals("10:00", dto.getHorario());
        assertEquals(2, dto.getCantGrupos());
        assertTrue(dto.getEstado());
        assertEquals(usuarioDto, dto.getUsuario());
        assertEquals(asignaturaDto, dto.getAsignatura());
        assertEquals(laboratorioDto, dto.getLaboratorio());
        assertEquals(insumos, dto.getInsumos());

        // Verificar toString
        assertNotNull(dto.toString());
    }
} 