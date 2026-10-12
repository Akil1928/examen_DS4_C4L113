import { Routes } from '@angular/router';
import { ListaCitasComponent } from './components/lista-citas/lista-citas.component';
import { CrearCitaComponent } from './components/crear-cita/crear-cita.component';

export const routes: Routes = [
  { path: 'citas', component: ListaCitasComponent },
  { path: 'crear-cita', component: CrearCitaComponent },
  { path: '', redirectTo: 'citas', pathMatch: 'full' },
  { path: '**', redirectTo: 'citas' }
];