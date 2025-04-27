package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.dto.CrearSolicitudInput;
import com.arquitectura.proyecto.dto.InsumoCantidadInput;
import com.arquitectura.proyecto.dto.SolicitudInput;
import com.arquitectura.proyecto.model.Solicitud;
import com.arquitectura.proyecto.service.PersonalABSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonalABSResolverTest {

    @Mock
    private PersonalABSService personalABSService;

    @InjectMocks
    private PersonalABSResolver personalABSResolver;

    private Solicitud solicitud;
    private CrearSolicitudInput crearSolicitudInput;
    private SolicitudInput solicitudInput;

    @BeforeEach
    void setUp() {
        solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setFechaUso(LocalDate.now());
        solicitud.setHorario(LocalTime.now());
        solicitud.setCantGrupos(2);
        solicitud.setEstado(false);

        crearSolicitudInput = new CrearSolicitudInput();
        crearSolicitudInput.setIdUsuario(1L);
        crearSolicitudInput.setIdAsignatura(1L);
        crearSolicitudInput.setIdLaboratorio(1L);
        crearSolicitudInput.setFechaUso(LocalDate.now().toString());
        crearSolicitudInput.setHorario(LocalTime.now().toString());
        crearSolicitudInput.setCantGrupos(2);

        InsumoCantidadInput insumoCantidad = new InsumoCantidadInput();
        insumoCantidad.setIdInsumo(1L);
        insumoCantidad.setCantidad(5.0);
        crearSolicitudInput.setInsumos(Arrays.asList(insumoCantidad));

        solicitudInput = new SolicitudInput();
        solicitudInput.setFechaUso(LocalDate.now().toString());
        solicitudInput.setHorario(LocalTime.now().toString());
        solicitudInput.setCantGrupos(3);
        solicitudInput.setEstado(true);
    }

    @Test
    void listarSolicitudes_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(personalABSService.listarSolicitudes()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSResolver.listarSolicitudes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(personalABSService, times(1)).listarSolicitudes();
    }

    @Test
    void listarSolicitudesRechazadas_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(personalABSService.listarSolicitudesRechazadas()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSResolver.listarSolicitudesRechazadas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(personalABSService, times(1)).listarSolicitudesRechazadas();
    }

    @Test
    void listarSolicitudesAprobadas_Success() {
        solicitud.setEstado(true);
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(personalABSService.listarSolicitudesAprobadas()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSResolver.listarSolicitudesAprobadas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(personalABSService, times(1)).listarSolicitudesAprobadas();
    }

    @Test
    void listarSolicitudesPorFechaUso_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(personalABSService.listarSolicitudesPorFechaUso()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSResolver.listarSolicitudesPorFechaUso();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(personalABSService, times(1)).listarSolicitudesPorFechaUso();
    }

    @Test
    void crearSolicitud_Success() {
        when(personalABSService.crearSolicitud(any())).thenReturn(solicitud);

        Solicitud result = personalABSResolver.crearSolicitud(crearSolicitudInput);

        assertNotNull(result);
        assertEquals(solicitud.getId(), result.getId());
        verify(personalABSService, times(1)).crearSolicitud(any());
    }

    @Test
    void modificarSolicitud_Success() {
        when(personalABSService.modificarSolicitud(anyLong(), any())).thenReturn(solicitud);

        Solicitud result = personalABSResolver.modificarSolicitud(1L, solicitudInput);

        assertNotNull(result);
        assertEquals(solicitud.getId(), result.getId());
        verify(personalABSService, times(1)).modificarSolicitud(anyLong(), any());
    }

    @Test
    void eliminarSolicitud_Success() {
        doNothing().when(personalABSService).eliminarSolicitud(anyLong());

        Boolean result = personalABSResolver.eliminarSolicitud(1L);

        assertTrue(result);
        verify(personalABSService, times(1)).eliminarSolicitud(anyLong());
    }

    @Test
    void solicitudesDelProfesor_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(personalABSService.listarSolicitudesDelUsuario(anyLong())).thenReturn(solicitudes);

        List<Solicitud> result = personalABSResolver.solicitudesDelProfesor(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(personalABSService, times(1)).listarSolicitudesDelUsuario(anyLong());
    }

    @Test
    void confirmarSolicitud_Success() {
        when(personalABSService.confirmarYActualizarSolicitud(anyLong())).thenReturn(solicitud);

        Solicitud result = personalABSResolver.confirmarSolicitud(1L);

        assertNotNull(result);
        assertEquals(solicitud.getId(), result.getId());
        verify(personalABSService, times(1)).confirmarYActualizarSolicitud(anyLong());
    }
} 