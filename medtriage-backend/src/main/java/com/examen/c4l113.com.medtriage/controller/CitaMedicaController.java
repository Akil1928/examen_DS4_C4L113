package com.examen.c4l113.com.medtriage.controller;

import com.examen.c4l113.com.medtriage.dto.CitaMedicaDTO;
import com.examen.c4l113.com.medtriage.dto.CrearCitaDTO;
import com.examen.c4l113.com.medtriage.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public ResponseEntity<List<CitaMedicaDTO>> obtenerTodas() {
        return ResponseEntity.ok(citaMedicaService.obtenerTodas());
    }

    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<List<CitaMedicaDTO>> obtenerPorPrioridad(@PathVariable String prioridad) {
        return ResponseEntity.ok(citaMedicaService.obtenerPorPrioridad(prioridad));
    }

    @PostMapping
    public ResponseEntity<CitaMedicaDTO> crearCita(@RequestBody CrearCitaDTO dto) {
        CitaMedicaDTO nuevaCita = citaMedicaService.crearCita(dto);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaMedicaDTO> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        CitaMedicaDTO citaActualizada = citaMedicaService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(citaActualizada);
    }
}