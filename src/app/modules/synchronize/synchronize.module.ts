import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialModule} from '../material/material.module';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { InicioSyncComponent } from './components/inicio-sync/inicio-sync.component';
import { RoutingSyncComponent } from './components/routing-sync/routing-sync.component';
import { CarouselModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ConfiguradorClienteComponent} from './components/configurador-cliente/configurador-cliente.component';
import {ModalUsuariosComponent} from '../modal/modal-usuarios/modal-usuarios.component';
import {TableroControlComponent} from './components/tablero-control/tablero-control.component';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import {ChartModule} from 'angular2-highcharts';


export function highchartsFactory() {
  const hc = require('highcharts');
  const h3 = require('highcharts/highcharts-3d');
  h3(hc);

  return hc;
}

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    ChartModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CarouselModule,
    WavesModule,
    ButtonsModule,
    FontAwesomeModule,
  ],
  declarations: [InicioSyncComponent, RoutingSyncComponent, ConfiguradorClienteComponent, TableroControlComponent],
  entryComponents: [ ModalUsuariosComponent],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }
  ]
})
export class SynchronizeModule { }
