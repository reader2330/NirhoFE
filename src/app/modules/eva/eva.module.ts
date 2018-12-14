import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Libs
import { MaterialModule } from '../material/material.module';
import { ChartModule } from 'angular2-highcharts';
import { AppRoutingModule } from '../../app-routing.module';
import { HttpClientModule } from '../../../../node_modules/@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PickerModule } from '@ctrl/ngx-emoji-mart';
import { EmojiModule } from '@ctrl/ngx-emoji-mart/ngx-emoji';

// Components
import { Sidebar360Component } from './components/sidebar-360/sidebar-360.component';
import { Steppers360Component } from './components/steppers-360/steppers-360.component';
import { DataCompany360Component } from './components/forms/data-company-360/data-company-360.component';
import { DataContact360Component } from './components/forms/data-contact-360/data-contact-360.component';
import { DataProject360Component } from './components/forms/data-project-360/data-project-360.component';
import { AssignPollComponent } from './components/assign-poll/assign-poll.component';
import { ViewAssignsComponent } from './components/view-assigns/view-assigns.component';
import { PollConfigComponent } from './components/poll-config/poll-config.component';
import { ViewPollComponent } from './components/view-poll/view-poll.component';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import { PollStatusComponent } from './components/poll-status/poll-status.component';
import {Bandeja360Component} from './components/bandeja-eva360/bandeja-evd360.component';
import {ConfiguradorCuestionario360Component} from './components/configurador-cuestionario-360/configurador-cuestionario-360.component';
import {CustomAnswer360Component} from './components/custom-answer-360/custom-answer-360.component';
import {CustomTheme360Component} from './components/custom-theme-360/custom-theme-360.component';
import {HeadCountEva360Component} from './components/head-count-eva360/head-count-eva360.component';
import {OrganigramaEva360Component} from './components/organigrama-eva360/organigrama-eva360.component';
import {CuestionarioEva360Component} from './components/cuestionario-eva360/cuestionario-eva360.component';
import {BandejaDetalleEva360Component} from './components/bandeja-detalle-eva360/bandeja-detalle-eva360.component';
import {AsignarConsultorEva360Component} from './components/asignar-consultor-eva360/asignar-consultor-eva360.component';
import {DataPeriodEva360Component} from './components/data-period-eva360/data-period-eva360.component';


export function highchartsFactory() {
  const hc = require('highcharts');
  const dd = require('highcharts/modules/drilldown');
  dd(hc);

  return hc;
}


@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    ChartModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    EmojiModule,
    PickerModule
  ],
  declarations: [
    Sidebar360Component,
    Steppers360Component,
    DataCompany360Component,
    DataContact360Component,
    DataProject360Component,
    AssignPollComponent,
    ViewAssignsComponent,
    PollConfigComponent,
    ViewPollComponent,
    PollStatusComponent,
    Bandeja360Component,
    ConfiguradorCuestionario360Component,
    CustomAnswer360Component,
    CustomTheme360Component,
    HeadCountEva360Component,
    OrganigramaEva360Component,
    CuestionarioEva360Component,
    BandejaDetalleEva360Component,
    AsignarConsultorEva360Component,
    DataPeriodEva360Component
  ],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }
    ]
})
export class EVAModule { }
