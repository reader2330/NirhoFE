import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InformacionFormComponent} from './informacion-form/informacion-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material/material.module';

@NgModule({
  imports: [
    MaterialModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [
    InformacionFormComponent
  ]
})
export class RYSModule { }
