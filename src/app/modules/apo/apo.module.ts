import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioApoComponent } from './components/inicio-apo/inicio-apo.component';
import { StepperApoComponent } from './components/stepper-apo/stepper-apo.component';
import { SidebarApoComponent } from './components/sidebar-apo/sidebar-apo.component';
import { EnterpriseApoComponent } from './components/enterprise-apo/enterprise-apo.component';
import { ProyectApoComponent } from './components/proyect-apo/proyect-apo.component';
import { HeadCountApoComponent } from './components/head-count-apo/head-count-apo.component';
import { OrganigramaApoComponent } from './components/organigrama-apo/organigrama-apo.component';
import { HaedCount2ApoComponent } from './components/haed-count2-apo/haed-count2-apo.component';
import {MaterialModule} from '../material/material.module';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BusquedaComponent } from './components/busqueda/busqueda.component';
import { BandejaListaApoComponent } from './components/bandeja-lista-apo/bandeja-lista-apo.component';
import { PollStatusApoComponent } from './components/poll-status-apo/poll-status-apo.component';
import { ContactApoComponent } from './components/contact-apo/contact-apo.component';
import { PeriodoApoComponent } from './components/periodo-apo/periodo-apo.component';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    //ChartModule.forRoot(required('highcharts')),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [
    InicioApoComponent,
    StepperApoComponent,
    SidebarApoComponent,
    EnterpriseApoComponent,
    ProyectApoComponent,
    HeadCountApoComponent,
    OrganigramaApoComponent,
    HaedCount2ApoComponent,
    BusquedaComponent,
    BandejaListaApoComponent,
    PollStatusApoComponent,
    ContactApoComponent,
    PeriodoApoComponent
  ]
})
export class ApoModule { }
