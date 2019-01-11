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
import {CandidatoTableComponent} from './candidato-table/candidato-table.component';
import {SelectorModalComponent} from '../modal/selector-modal/selector-modal.component';
import {ModalComentarioComponent} from '../modal/modal-comentario/modal-comentario.component';
import {SolicitanteBandejaComponent} from './solicitante-bandeja/solicitante-bandeja.component';
import {ModalContratoComponent} from '../modal/modal-contrato/modal-contrato.component';

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
    CandidatoTableComponent,
    SelectorModalComponent,
    ModalComentarioComponent,
    SolicitanteBandejaComponent,
    ModalContratoComponent
  ],
  entryComponents: [
    SelectorModalComponent,
    ModalComentarioComponent,
    ModalContratoComponent
  ],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }
  ]
})
export class RYSModule { }
