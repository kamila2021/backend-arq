package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.dto.AsignaturaDto;
import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.repository.AsignaturaRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    public Asignatura createSubject(AsignaturaDto asignaturaDto) {
        Asignatura asignatura = new Asignatura();
        asignatura.setName(asignaturaDto.getName());
        asignatura.setCode(asignaturaDto.getCode());
        return asignaturaRepository.save(asignatura);
    }

    @PreAuthorize("isAuthenticated()")
    public List<Asignatura> listarAsignaturas() {
        return asignaturaRepository.findAll();
    }

}
