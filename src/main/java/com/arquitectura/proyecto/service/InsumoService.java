package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.InsumoInput;
import com.arquitectura.proyecto.model.Insumo;
import com.arquitectura.proyecto.repository.InsumoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public Insumo crearInsumo(InsumoInput input) {
        Insumo insumo = new Insumo();
        insumo.setNombre(input.getNombre());
        insumo.setTipo(input.getTipo());
        insumo.setUnidadMedida(input.getUnidadMedida());
        insumo.setCantidad(input.getCantidad());
        insumo.setStockDisponible(input.getStockDisponible());
        return insumoRepository.save(insumo);
    }

    public Insumo modificarInsumo(Long id, InsumoInput input) {
        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        insumo.setNombre(input.getNombre());
        insumo.setTipo(input.getTipo());
        insumo.setUnidadMedida(input.getUnidadMedida());
        insumo.setCantidad(input.getCantidad());
        insumo.setStockDisponible(input.getStockDisponible());

        return insumoRepository.save(insumo);
    }

    public void eliminarInsumo(Long id) {
        insumoRepository.deleteById(id);
    }

    public List<Insumo> listarInsumos() {
        return insumoRepository.findAll();
    }
}
