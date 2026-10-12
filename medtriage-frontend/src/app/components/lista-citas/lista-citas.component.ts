import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CitaMedicaService } from '../../services/cita-medica.service';
import { CitaMedicaDTO } from '../../models/cita.model';

@Component({
  selector: 'app-lista-citas',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container mt-4">
      <h2>Listado de Citas Médicas</h2>
      
      <div *ngIf="citas.length === 0" class="alert alert-info">
        No hay citas registradas en el sistema.
      </div>

      <table class="table table-striped" *ngIf="citas.length > 0">
        <thead>
          <tr>
            <th>Código</th>
            <th>Paciente</th>
            <th>Doctor</th>
            <th>Prioridad</th>
            <th>Motivo</th>
            <th>Estado</th>
            <th>Monto</th>
            <th>Fecha</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let cita of citas">
            <td>{{ cita.codigoCita }}</td>
            <td>{{ cita.nombrePaciente }}</td>
            <td>{{ cita.nombreDoctor }}</td>
            <td>
              <span class="badge" [ngClass]="{
                'bg-danger': cita.nivelPrioridad === 'ALTA',
                'bg-warning text-dark': cita.nivelPrioridad === 'MEDIA',
                'bg-success': cita.nivelPrioridad === 'BAJA'
              }">
                {{ cita.nivelPrioridad }}
              </span>
            </td>
            <td>{{ cita.motivoConsulta }}</td>
            <td>{{ cita.estado }}</td>
            <td>\${{ cita.montoConsulta }}</td>
            <td>{{ cita.fechaCita | date:'short' }}</td>
            <td>
              <button class="btn btn-sm btn-success" (click)="cambiarEstado(cita.id, 'ATENDIDA')">Atender</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class ListaCitasComponent implements OnInit {
  private citaService = inject(CitaMedicaService);
  citas: CitaMedicaDTO[] = [];

  ngOnInit(): void {
    this.cargarCitas();
  }

  cargarCitas() {
    this.citaService.obtenerCitas().subscribe({
      next: (data) => this.citas = data,
      error: (err) => console.error('Error al cargar citas:', err)
    });
  }

  cambiarEstado(id: number, estado: string) {
    this.citaService.actualizarEstado(id, estado).subscribe({
      next: () => this.cargarCitas(),
      error: (err) => console.error('Error al actualizar estado:', err)
    });
  }
}