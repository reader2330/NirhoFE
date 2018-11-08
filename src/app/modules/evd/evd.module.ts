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
import {TableClienteEvdComponent} from './components/table-cliente-evd/table-cliente-evd.component';
import { TableCliente2EvdComponent } from './components/table-cliente2-evd/table-cliente2-evd.component';
import { TableClient2ModalEvdComponent } from './components/modals/table-client2-modal-evd/table-client2-modal-evd.component';

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
    InicioEvdComponent,
    SidebarEvdComponent,
    LoginEvdComponent,
    BandejaEvdComponent,
    BandejaDetalleEvdComponent,
    HeadCountEvdComponent,
    OrganigramaEvdComponent,
    TableClienteEvdComponent,
    TableCliente2EvdComponent,
    TableClient2ModalEvdComponent
  ],
  entryComponents: [
    TableClient2ModalEvdComponent
  ]
})
export class EvdModule { }
/*@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})*/
