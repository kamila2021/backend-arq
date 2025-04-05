package com.arquitectura.proyecto.resolver;

import com.arquitectura.proyecto.model.Asignatura;
import com.arquitectura.proyecto.service.AsignaturaService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AsignaturaResolver {

    private final AsignaturaService asignaturaService;

    public AsignaturaResolver(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @QueryMapping
    public List<Asignatura> obtenerTodasAsignaturas() {
        try{
            return asignaturaService.obtenerTodasAsignaturas();
        }catch (Exception e){
            throw new RuntimeException("Error al obtener las asignaturas", e);
        }
    }
}
