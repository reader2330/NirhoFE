import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {GeneradorReportesComponent} from './components/generador-reportes/generador-reportes.component';
import {MaterialModule} from '../material/material.module';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
  ],
  declarations: [
    GeneradorReportesComponent
  ],
  exports: [GeneradorReportesComponent]
})
export class SharedModule { }
