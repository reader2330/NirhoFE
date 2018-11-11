import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './modules/material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ChartModule} from 'angular2-highcharts';
import {IrhModule} from './modules/irh/irh.module';
import {CLBModule} from './modules/clb/clb.module';
import {EvdModule} from './modules/evd/evd.module';
import {AdmModule} from './modules/adm/adm.module';





@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ChartModule.forRoot(require('highcharts')),
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    CLBModule,
    FormsModule,
    ReactiveFormsModule,
    IrhModule,
    EvdModule,
    AdmModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
