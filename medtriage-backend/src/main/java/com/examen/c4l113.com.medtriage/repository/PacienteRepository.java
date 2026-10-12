package com.examen.c4l113.com.medtriage.repository;

import com.examen.c4l113.com.medtriage.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}