package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.CrearSolicitudInput;
import com.arquitectura.proyecto.dto.InsumoCantidadInput;
import com.arquitectura.proyecto.dto.SolicitudInput;
import com.arquitectura.proyecto.model.*;
import com.arquitectura.proyecto.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonalABSServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InsumoRepository insumoRepository;

    @Mock
    private SolicitudInsumoRepository solicitudInsumoRepository;

    @InjectMocks
    private PersonalABSService personalABSService;

    private Solicitud solicitud;
    private Usuario usuario;
    private Asignatura asignatura;
    private Laboratorio laboratorio;
    private Insumo insumo;
    private CrearSolicitudInput crearSolicitudInput;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");

        asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Asignatura Test");

        laboratorio = new Laboratorio();
        laboratorio.setId(1L);
        laboratorio.setNombre("Laboratorio Test");

        insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNombre("Insumo Test");
        insumo.setStockDisponible(100);

        solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setUsuario(usuario);
        solicitud.setAsignatura(asignatura);
        solicitud.setLaboratorio(laboratorio);
        solicitud.setFechaSolicitud(LocalDate.now());
        solicitud.setFechaUso(LocalDate.now().plusDays(1));
        solicitud.setHorario(LocalTime.now());
        solicitud.setCantGrupos(2);
        solicitud.setEstado(false);

        crearSolicitudInput = new CrearSolicitudInput();
        crearSolicitudInput.setIdUsuario(1L);
        crearSolicitudInput.setIdAsignatura(1L);
        crearSolicitudInput.setIdLaboratorio(1L);
        crearSolicitudInput.setFechaUso(LocalDate.now().plusDays(1).toString());
        crearSolicitudInput.setHorario(LocalTime.now().toString());
        crearSolicitudInput.setCantGrupos(2);

        InsumoCantidadInput insumoCantidad = new InsumoCantidadInput();
        insumoCantidad.setIdInsumo(1L);
        insumoCantidad.setCantidad(5.0);
        crearSolicitudInput.setInsumos(Arrays.asList(insumoCantidad));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void listarSolicitudes_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(solicitudRepository.findAll()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSService.listarSolicitudes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(solicitudRepository, times(1)).findAll();
    }

    @Test
    @WithMockUser
    void listarSolicitudesDelUsuario_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(solicitudRepository.findByUsuarioIdOrderByFechaUsoAsc(1L)).thenReturn(solicitudes);

        List<Solicitud> result = personalABSService.listarSolicitudesDelUsuario(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(solicitudRepository, times(1)).findByUsuarioIdOrderByFechaUsoAsc(1L);
    }

    @Test
    @WithMockUser(roles = "Admin")
    void listarSolicitudesRechazadas_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(solicitudRepository.findByEstado(false)).thenReturn(solicitudes);

        List<Solicitud> result = personalABSService.listarSolicitudesRechazadas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(solicitudRepository, times(1)).findByEstado(false);
    }

    @Test
    @WithMockUser(roles = "Admin")
    void listarSolicitudesAprobadas_Success() {
        solicitud.setEstado(true);
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(solicitudRepository.findByEstado(true)).thenReturn(solicitudes);

        List<Solicitud> result = personalABSService.listarSolicitudesAprobadas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(solicitudRepository, times(1)).findByEstado(true);
    }

    @Test
    @WithMockUser(roles = "Admin")
    void listarSolicitudesPorFechaUso_Success() {
        List<Solicitud> solicitudes = Arrays.asList(solicitud);
        when(solicitudRepository.findAllByOrderByFechaUsoAsc()).thenReturn(solicitudes);

        List<Solicitud> result = personalABSService.listarSolicitudesPorFechaUso();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(solicitud.getId(), result.get(0).getId());
        verify(solicitudRepository, times(1)).findAllByOrderByFechaUsoAsc();
    }

    @Test
    @WithMockUser
    void crearSolicitud_Success() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignatura));
        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(laboratorio));
        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);
        when(solicitudInsumoRepository.save(any(SolicitudInsumo.class))).thenReturn(new SolicitudInsumo());

        Solicitud result = personalABSService.crearSolicitud(crearSolicitudInput);

        assertNotNull(result);
        assertEquals(solicitud.getId(), result.getId());
        verify(solicitudRepository, times(1)).save(any(Solicitud.class));
        verify(solicitudInsumoRepository, times(1)).save(any(SolicitudInsumo.class));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void modificarSolicitud_Success() {
        SolicitudInput input = new SolicitudInput();
        input.setFechaUso(LocalDate.now().plusDays(2).toString());
        input.setHorario(LocalTime.now().plusHours(1).toString());
        input.setCantGrupos(3);
        input.setEstado(true);

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        Solicitud result = personalABSService.modificarSolicitud(1L, new Solicitud());

        assertNotNull(result);
        assertEquals(solicitud.getId(), result.getId());
        verify(solicitudRepository, times(1)).findById(1L);
        verify(solicitudRepository, times(1)).save(any(Solicitud.class));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void eliminarSolicitud_Success() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(solicitudInsumoRepository.findBySolicitudId(1L)).thenReturn(Arrays.asList(new SolicitudInsumo()));
        doNothing().when(solicitudInsumoRepository).deleteAll(anyList());
        doNothing().when(solicitudRepository).delete(any(Solicitud.class));

        personalABSService.eliminarSolicitud(1L);

        verify(solicitudRepository, times(1)).findById(1L);
        verify(solicitudInsumoRepository, times(1)).findBySolicitudId(1L);
        verify(solicitudInsumoRepository, times(1)).deleteAll(anyList());
        verify(solicitudRepository, times(1)).delete(any(Solicitud.class));
    }

    @Test
    @WithMockUser(roles = "Admin")
    void confirmarYActualizarSolicitud_Success() {
        SolicitudInsumo solicitudInsumo = new SolicitudInsumo();
        solicitudInsumo.setInsumo(insumo);
        solicitudInsumo.setCantidad(5.0);

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(solicitudInsumoRepository.findBySolicitudId(1L)).thenReturn(Arrays.asList(solicitudInsumo));
        when(insumoRepository.save(any(Insumo.class))).thenReturn(insumo);
        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        Solicitud result = personalABSService.confirmarYActualizarSolicitud(1L);

        assertNotNull(result);
        assertTrue(result.getEstado());
        verify(solicitudRepository, times(1)).findById(1L);
        verify(solicitudInsumoRepository, times(1)).findBySolicitudId(1L);
        verify(insumoRepository, times(1)).save(any(Insumo.class));
        verify(solicitudRepository, times(1)).save(any(Solicitud.class));
    }
} 