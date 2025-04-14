package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.LaboratorioDto;
import com.arquitectura.proyecto.model.Laboratorio;
import com.arquitectura.proyecto.repository.LaboratorioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
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
