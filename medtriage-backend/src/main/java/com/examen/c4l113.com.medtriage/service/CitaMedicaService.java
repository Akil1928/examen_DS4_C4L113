package com.examen.c4l113.com.medtriage.service;

import com.examen.c4l113.com.medtriage.dto.CitaMedicaDTO;
import com.examen.c4l113.com.medtriage.dto.CrearCitaDTO;
import com.examen.c4l113.com.medtriage.model.CitaMedica;
import com.examen.c4l113.com.medtriage.model.Doctor;
import com.examen.c4l113.com.medtriage.model.Paciente;
import com.examen.c4l113.com.medtriage.repository.CitaMedicaRepository;
import com.examen.c4l113.com.medtriage.repository.DoctorRepository;
import com.examen.c4l113.com.medtriage.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<CitaMedicaDTO> obtenerTodas() {
        return citaMedicaRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<CitaMedicaDTO> obtenerPorPrioridad(String prioridad) {
        return citaMedicaRepository.findByNivelPrioridadIgnoreCaseOrderByFechaCitaDesc(prioridad)
                .stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public CitaMedicaDTO crearCita(CrearCitaDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        
        if (!doctor.isDisponible()) {
            throw new RuntimeException("El doctor seleccionado no se encuentra disponible");
        }

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        CitaMedica cita = new CitaMedica();
        cita.setCodigoCita("CIT-2026-" + (int)(Math.random() * 9000 + 1000));
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setNivelPrioridad(dto.getNivelPrioridad());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado("PENDIENTE");
        cita.setMontoConsulta(dto.getMontoConsulta());
        cita.setFechaCita(LocalDateTime.now());

        CitaMedica guardada = citaMedicaRepository.save(cita);
        return convertirADTO(guardada);
    }

    public CitaMedicaDTO actualizarEstado(Long id, String nuevoEstado) {
        CitaMedica cita = citaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita médica no encontrada"));
        cita.setEstado(nuevoEstado);
        return convertirADTO(citaMedicaRepository.save(cita));
    }

    private CitaMedicaDTO convertirADTO(CitaMedica cita) {
        return new CitaMedicaDTO(
                cita.getId(),
                cita.getCodigoCita(),
                cita.getPaciente().getNombreCompleto(),
                cita.getDoctor().getNombre(),
                cita.getNivelPrioridad(),
                cita.getMotivoConsulta(),
                cita.getEstado(),
                cita.getMontoConsulta(),
                cita.getFechaCita()
        );
    }
}