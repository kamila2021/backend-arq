package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.AsignaturaDto;
import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.repository.AsignaturaRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public Asignatura crearAsignatura(AsignaturaDto input) {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(input.getNombre());
        asignatura.setCodigo(input.getCodigo());
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> listarAsignaturas() {
        return asignaturaRepository.findAll();
    }

    // public InsumoService(InsumoRepository insumoRepository) {
    //     this.insumoRepository = insumoRepository;
    // }

    // public Insumo crearInsumo(InsumoInput input) {
    //     Insumo insumo = new Insumo();
    //     insumo.setNombre(input.getNombre());
    //     insumo.setTipo(input.getTipo());
    //     insumo.setUnidadMedida(input.getUnidadMedida());
    //     insumo.setCantidad(input.getCantidad());
    //     insumo.setStockDisponible(input.getStockDisponible());
    //     return insumoRepository.save(insumo);
    // }

    // public Insumo modificarInsumo(Long id, InsumoInput input) {
    //     Insumo insumo = insumoRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

    //     insumo.setNombre(input.getNombre());
    //     insumo.setTipo(input.getTipo());
    //     insumo.setUnidadMedida(input.getUnidadMedida());
    //     insumo.setCantidad(input.getCantidad());
    //     insumo.setStockDisponible(input.getStockDisponible());

    //     return insumoRepository.save(insumo);
    // }

    // public void eliminarInsumo(Long id) {
    //     insumoRepository.deleteById(id);
    // }

    // public List<Insumo> listarInsumos() {
    //     return insumoRepository.findAll();
    // }
}
