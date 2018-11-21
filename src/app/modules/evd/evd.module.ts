import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioEvdComponent } from './components/inicio-evd/inicio-evd.component';
import { SidebarEvdComponent } from './components/sidebar-evd/sidebar-evd.component';
import { LoginEvdComponent } from './components/login-evd/login-evd.component';
import {MaterialModule} from '../material/material.module';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BandejaEvdComponent } from './components/bandeja-evd/bandeja-evd.component';
import { BandejaDetalleEvdComponent } from './components/bandeja-detalle-evd/bandeja-detalle-evd.component';
import { HeadCountEvdComponent } from './components/head-count-evd/head-count-evd.component';
import { OrganigramaEvdComponent } from './components/organigrama-evd/organigrama-evd.component';
import { TableCliente2EvdComponent } from './components/table-cliente2-evd/table-cliente2-evd.component';
import { TableClient2ModalEvdComponent } from './components/modals/table-client2-modal-evd/table-client2-modal-evd.component';
import { StepperEvdComponent } from './components/stepper-evd/stepper-evd.component';
import { EnterpriseEvdComponent } from './components/enterprise-evd/enterprise-evd.component';
import { ProyectEvdComponent } from './components/proyect-evd/proyect-evd.component';
import { CuestionarioEvdComponent } from './components/cuestionario-evd/cuestionario-evd.component';
import { EncuestaEvdComponent } from './components/encuesta-evd/encuesta-evd.component';
import { EncuestaModalEvdComponent } from './components/encuesta-modal-evd/encuesta-modal-evd.component';
import {EmojiModule} from '@ctrl/ngx-emoji-mart/ngx-emoji';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    EmojiModule,
  ],
  declarations: [
    InicioEvdComponent,
    SidebarEvdComponent,
    LoginEvdComponent,
    BandejaEvdComponent,
    BandejaDetalleEvdComponent,
    HeadCountEvdComponent,
    OrganigramaEvdComponent,

    TableCliente2EvdComponent,
    TableClient2ModalEvdComponent,
    StepperEvdComponent,
    EnterpriseEvdComponent,
    ProyectEvdComponent,
    CuestionarioEvdComponent,
    EncuestaEvdComponent,
    EncuestaModalEvdComponent
  ],
  entryComponents: [
    TableClient2ModalEvdComponent,
    EncuestaModalEvdComponent
  ]
})
export class EvdModule { }
/*@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})*/
