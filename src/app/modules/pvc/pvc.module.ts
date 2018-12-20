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
    BandejaPVCComponent

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
