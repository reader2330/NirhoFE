import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InformacionFormComponent} from './informacion-form/informacion-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material/material.module';
import {VacanteTableComponent} from './vacante-table/vacante-table.component';
import {SidebarRysComponent} from './sidebar-rys/sidebar-rys.component';
import {EstadisticasVacantesComponent} from './estadisticas-vacantes/estadisticas-vacantes.component';
import {CandidatoFormComponent} from './candidato-form/candidato-form.component';

@NgModule({
  imports: [
    MaterialModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  declarations: [
    InformacionFormComponent,
    VacanteTableComponent,
    SidebarRysComponent,
    EstadisticasVacantesComponent,
    CandidatoFormComponent
  ]
})
export class RYSModule { }
