import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
      <a class="navbar-brand" href="#">MedTriage Express</a>
      <div class="navbar-nav">
        <a class="nav-link" routerLink="/citas" routerLinkActive="active">Listado de Citas</a>
        <a class="nav-link" routerLink="/crear-cita" routerLinkActive="active">Nueva Cita</a>
      </div>
    </nav>

    <div class="container mt-4">
      <router-outlet></router-outlet>
    </div>
  `
})
export class App {}