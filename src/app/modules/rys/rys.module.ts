import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InformacionFormComponent} from './informacion-form/informacion-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material/material.module';
import {VacanteTableComponent} from './vacante-table/vacante-table.component';
import {SidebarRysComponent} from './sidebar-rys/sidebar-rys.component';
import {EstadisticasVacantesComponent} from './estadisticas-vacantes/estadisticas-vacantes.component';
import {CandidatoFormComponent} from './candidato-form/candidato-form.component';
import {SharedModule} from '../shared/shared.module';
import {ChartModule} from 'angular2-highcharts';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';

export function highchartsFactory() {
  const hc = require('highcharts');
  const h3 = require('highcharts/highcharts-3d');
  h3(hc);

  return hc;
}
@NgModule({
  imports: [
    MaterialModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    ChartModule,

  ],
  declarations: [
    InformacionFormComponent,
    VacanteTableComponent,
    SidebarRysComponent,
    EstadisticasVacantesComponent,
    CandidatoFormComponent,
  ],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }
  ]
})
export class RYSModule { }
