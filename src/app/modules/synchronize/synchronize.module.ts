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

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    //ChartModule.forRoot(required('highcharts')),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CarouselModule,
    WavesModule,
    ButtonsModule,
    FontAwesomeModule
  ],
  declarations: [InicioSyncComponent, RoutingSyncComponent]
})
export class SynchronizeModule { }