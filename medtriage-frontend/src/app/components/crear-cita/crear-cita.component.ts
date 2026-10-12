import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CitaMedicaService } from '../../services/cita-medica.service';
import { CrearCitaDTO } from '../../models/cita.model';

@Component({
  selector: 'app-crear-cita',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container mt-4">
      <h2>Registrar Nueva Cita Médica</h2>
      
      <form (ngSubmit)="guardarCita()" #form="ngForm">
        <div class="mb-3">
          <label class="form-label">ID Paciente</label>
          <input type="number" class="form-control" [(ngModel)]="nuevaCita.pacienteId" name="pacienteId" required>
        </div>

        <div class="mb-3">
          <label class="form-label">ID Doctor</label>
          <input type="number" class="form-control" [(ngModel)]="nuevaCita.doctorId" name="doctorId" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Nivel de Prioridad</label>
          <select class="form-control" [(ngModel)]="nuevaCita.nivelPrioridad" name="nivelPrioridad" required>
            <option value="BAJA">BAJA</option>
            <option value="MEDIA">MEDIA</option>
            <option value="ALTA">ALTA</option>
          </select>
        </div>

        <div class="mb-3">
          <label class="form-label">Motivo de Consulta</label>
          <textarea class="form-control" [(ngModel)]="nuevaCita.motivoConsulta" name="motivoConsulta" required></textarea>
        </div>

        <div class="mb-3">
          <label class="form-label">Monto de Consulta</label>
          <input type="number" step="0.01" class="form-control" [(ngModel)]="nuevaCita.montoConsulta" name="montoConsulta" required>
        </div>

        <button type="submit" class="btn btn-primary" [disabled]="form.invalid">Guardar Cita</button>
      </form>
    </div>
  `
})
export class CrearCitaComponent {
  private citaService = inject(CitaMedicaService);

  nuevaCita: CrearCitaDTO = {
    pacienteId: 1,
    doctorId: 1,
    nivelPrioridad: 'MEDIA',
    motivoConsulta: '',
    montoConsulta: 25000.00
  };

  guardarCita() {
    this.citaService.crearCita(this.nuevaCita).subscribe({
      next: (response) => {
        alert('Cita creada exitosamente con código: ' + response.codigoCita);
        // Aquí puedes redirigir o limpiar el formulario
      },
      error: (err) => {
        console.error('Error al crear la cita:', err);
        alert('Error al registrar la cita.');
      }
    });
  }
}