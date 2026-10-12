package com.examen.c4l113.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedicaDTO {
    private Long id;
    private String codigoCita;
    private String nombrePaciente;
    private String nombreDoctor;
    private String nivelPrioridad;
    private String motivoConsulta;
    private String estado;
    private BigDecimal montoConsulta;
    private LocalDateTime fechaCita;
}