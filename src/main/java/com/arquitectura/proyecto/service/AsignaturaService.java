package com.arquitectura.proyecto.service;

import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> obtenerTodasAsignaturas() {
        List<Asignatura> asignaturas = asignaturaRepository.findAll();
        System.out.println("Asignaturas----------------------------: " + asignaturas);
        return asignaturas;
    }
}
