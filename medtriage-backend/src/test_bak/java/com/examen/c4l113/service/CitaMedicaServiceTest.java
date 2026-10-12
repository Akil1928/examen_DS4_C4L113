package com.examen.c4l113.service;

import com.examen.c4l113.dto.CitaMedicaDTO;
import com.examen.c4l113.dto.CrearCitaDTO;
import com.examen.c4l113.model.CitaMedica;
import com.examen.c4l113.model.Doctor;
import com.examen.c4l113.model.Paciente;
import com.examen.c4l113.repository.CitaMedicaRepository;
import com.examen.c4l113.repository.DoctorRepository;
import com.examen.c4l113.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @Test
    void crearCita_Exitoso() {
        // Arrange
        CrearCitaDTO crearDTO = new CrearCitaDTO(1L, 1L, "ALTA", "Dolor en el pecho", new BigDecimal("25000.00"));

        Doctor doctor = new Doctor(1L, "Dr. Carlos Alvarado", "Medicina General", true);
        Paciente paciente = new Paciente(1L, "118230495", "Elena Madrigal Castro", "elena@ucr.ac.cr", "88776655");

        CitaMedica citaGuardada = new CitaMedica();
        citaGuardada.setId(1L);
        citaGuardada.setCodigoCita("CIT-2026-001");
        citaGuardada.setPaciente(paciente);
        citaGuardada.setDoctor(doctor);
        citaGuardada.setNivelPrioridad("ALTA");
        citaGuardada.setMotivoConsulta("Dolor en el pecho");
        citaGuardada.setEstado("PENDIENTE");
        citaGuardada.setMontoConsulta(new BigDecimal("25000.00"));
        citaGuardada.setFechaCita(LocalDateTime.now());

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaGuardada);

        // Act
        CitaMedicaDTO resultado = citaMedicaService.crearCita(crearDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("ALTA", resultado.getNivelPrioridad());
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals("Dr. Carlos Alvarado", resultado.getNombreDoctor());
        verify(citaMedicaRepository, times(1)).save(any(CitaMedica.class));
    }
}