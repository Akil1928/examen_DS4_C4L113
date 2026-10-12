import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CitaMedicaDTO, CrearCitaDTO } from '../models/cita.model';

@Injectable({
  providedIn: 'root'
})
export class CitaMedicaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/v1/citas';

  obtenerCitas(): Observable<CitaMedicaDTO[]> {
    return this.http.get<CitaMedicaDTO[]>(this.apiUrl);
  }

  crearCita(cita: CrearCitaDTO): Observable<CitaMedicaDTO> {
    return this.http.post<CitaMedicaDTO>(this.apiUrl, cita);
  }

  actualizarEstado(id: number, estado: string): Observable<CitaMedicaDTO> {
    return this.http.patch<CitaMedicaDTO>(`${this.apiUrl}/${id}/estado`, { estado });
  }
}