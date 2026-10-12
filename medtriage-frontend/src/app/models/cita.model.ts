export interface CitaMedicaDTO {
  id: number;
  codigoCita: string;
  nombrePaciente: string;
  nombreDoctor: string;
  nivelPrioridad: string;
  motivoConsulta: string;
  estado: string;
  montoConsulta: number;
  fechaCita: string;
}

export interface CrearCitaDTO {
  pacienteId: number;
  doctorId: number;
  nivelPrioridad: string;
  motivoConsulta: string;
  montoConsulta: number;
}