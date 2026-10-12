package com.examen.c4l113.com.medtriage.repository;

import com.examen.c4l113.com.medtriage.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}