package com.examen.c4l113.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearCitaDTO {
    private Long pacienteId;
    private Long doctorId;
    private String nivelPrioridad; 
    private String motivoConsulta;
    private BigDecimal montoConsulta;
}