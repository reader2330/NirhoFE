import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './modules/material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { FormularioPrimaryComponent } from './components/formularios/formulario-primary/formulario-primary.component';
import { DataCompanyComponent } from './components/formularios/data-company/data-company.component';
import { DataProyectComponent } from './components/formularios/data-proyect/data-proyect.component';
import { DataPeriodComponent } from './components/formularios/data-period/data-period.component';
import { HeadCountComponent } from './components/formularios/head-count/head-count.component';
import { OrganigramaComponent } from './components/formularios/organigrama/organigrama.component';
import { SteppersComponent } from './components/formularios/steppers/steppers.component';
import { DataContactComponent } from './components/formularios/data-contact/data-contact.component';



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
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
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
