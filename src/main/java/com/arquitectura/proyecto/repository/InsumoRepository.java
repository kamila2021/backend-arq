package com.arquitectura.proyecto.repository;

import com.arquitectura.proyecto.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}
