package com.examen.c4l113.repository;

import com.examen.c4l113.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    
    List<CitaMedica> findByNivelPrioridadIgnoreCaseOrderByFechaCitaDesc(String nivelPrioridad);
}