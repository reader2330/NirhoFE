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


@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    ChartModule.forRoot(require('highcharts')),
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
    DataProject360Component
  ]
})
export class EVAModule { }
