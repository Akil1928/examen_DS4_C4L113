package com.examen.c4l113.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita_medica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cita", nullable = false, unique = true, length = 30)
    private String codigoCita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "nivel_prioridad", nullable = false, length = 20)
    private String nivelPrioridad;

    @Column(name = "motivo_consulta", nullable = false, length = 255)
    private String motivoConsulta;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "monto_consulta", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoConsulta;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDateTime fechaCita;
}