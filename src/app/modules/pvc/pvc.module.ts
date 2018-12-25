import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material/material.module';
import {highchartsFactory} from '../clb/clb.module';
import {ChartModule} from 'angular2-highcharts';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import {SidebarPvcComponent} from './components/sidebar-pvc/sidebar-pvc.component';
import {SteppersPvcComponent} from './components/steppers-pvc/steppers-pvc.component';
import {BandejaDetallePvcComponent} from './components/bandeja-detalle-pvc/bandeja-detalle-pvc.component';
import {BandejaPVCComponent} from './components/bandeja-pvc/bandeja-pvc.component';
import {SelectorAreasComponent} from './components/selector-areas/selector-areas.component';
import {DetallePreguntasPvcComponent} from './components/detalle-preguntas-pvc/detalle-preguntas-pvc.component';
import {DataCompanyPvcComponent} from './components/forms-pvc/data-company-pvc/data-company-pvc.component';
import {DataContactPvcComponent} from './components/forms-pvc/data-contact-pvc/data-contact-pvc.component-';
import {DataProjectPvcComponent} from './components/forms-pvc/data-project-pvc/data-project-pvc.component';
import {AsignarConsultorPvcComponent} from './components/asignar-consultor-pvc/asignar-consultor-pvc.component';
import {PeriodoPvcComponent} from './components/periodo-pvc/periodo-pvc.component';
import {SelectoEsferasComponent} from './components/selecto-esferas/selecto-esferas.component';
import {SelectorNivelComponent} from './components/selector-nivel/selector-nivel.component';
import {SelectoEspecialidadesComponent} from './components/selecto-especialidades/selecto-especialidades.component';
import {SelectorConocimientoTecnicosComponent} from './components/selector-conocimiento-tecnicos/selector-conocimiento-tecnicos.component';
import {HeadCountPvcComponent} from './components/head-count-pvc/head-count-pvc.component';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ChartModule,
  ],
  declarations: [
    SidebarPvcComponent,
    SteppersPvcComponent,
    BandejaDetallePvcComponent,
    BandejaPVCComponent,
    SelectorAreasComponent,
    DetallePreguntasPvcComponent,
    DataCompanyPvcComponent,
    DataContactPvcComponent,
    DataProjectPvcComponent,
    AsignarConsultorPvcComponent,
    PeriodoPvcComponent,
    SelectoEsferasComponent,
    SelectorNivelComponent,
    SelectoEspecialidadesComponent,
    SelectorConocimientoTecnicosComponent,
    HeadCountPvcComponent,

  ],
  entryComponents: [

  ],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }]
})
export class PvcModule { }
