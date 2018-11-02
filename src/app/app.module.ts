import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './modules/clb/components/login/login.component';
import { InicioComponent } from './modules/clb/components/inicio/inicio.component';
import { SidebarComponent } from './modules/clb/components/sidebar/sidebar.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './modules/material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { FormularioPrimaryComponent } from './modules/clb/components/formularios/formulario-primary/formulario-primary.component';
import { DataCompanyComponent } from './modules/clb/components/formularios/data-company/data-company.component';
import { DataProyectComponent } from './modules/clb/components/formularios/data-proyect/data-proyect.component';
import { DataPeriodComponent } from './modules/clb/components/formularios/data-period/data-period.component';
import { HeadCountComponent } from './modules/clb/components/formularios/head-count/head-count.component';
import { OrganigramaComponent } from './modules/clb/components/formularios/organigrama/organigrama.component';
import { SteppersComponent } from './modules/clb/components/formularios/steppers/steppers.component';
import { DataContactComponent } from './modules/clb/components/formularios/data-contact/data-contact.component';
import {BandejaComponent} from './modules/clb/components/bandeja/bandeja.component';
import { BandejaDetalleComponent } from './modules/clb/components/bandeja-detalle/bandeja-detalle.component';
import {ChartModule} from 'angular2-highcharts';
import { CuestionarioSelectComponent } from './modules/clb/components/cuestionario-select/cuestionario-select.component';
import { AsignarConsultorComponent } from './modules/clb/components/asignar-consultor/asignar-consultor.component';
import { EstadisticasComponent } from './modules/clb/components/estadisticas/estadisticas.component';
import { DetallePreguntasComponent } from './modules/clb/components/detalle-preguntas/detalle-preguntas.component';

import { EncuestaComponent } from './modules/clb/components/encuesta/encuesta.component';

import { AvatarEditComponent } from './modules/clb/components/avatar-edit/avatar-edit.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    InicioComponent,
    SidebarComponent,
    FormularioPrimaryComponent,
    DataCompanyComponent,
    DataProyectComponent,
    DataPeriodComponent,
    HeadCountComponent,
    OrganigramaComponent,
    SteppersComponent,
    DataContactComponent,
    BandejaComponent,
    BandejaDetalleComponent,
    CuestionarioSelectComponent,
    AsignarConsultorComponent,
    EstadisticasComponent,
    DetallePreguntasComponent,
    EncuestaComponent,
    AvatarEditComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ChartModule.forRoot(require('highcharts')),
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
