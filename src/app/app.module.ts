import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './modules/material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {IrhModule} from './modules/irh/irh.module';
import {CLBModule} from './modules/clb/clb.module';
<<<<<<< HEAD

import {EVAModule} from './modules/eva/eva.module';


=======
import {EvdModule} from './modules/evd/evd.module';
import {AdmModule} from './modules/adm/adm.module';
import {SynchronizeModule} from './modules/synchronize/synchronize.module';
import {ApoModule} from './modules/apo/apo.module';
>>>>>>> 0f02b62f5d4e1306b3ccdd02aa05a60c9889f81e


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    CLBModule,
    EVAModule,
    FormsModule,
    ReactiveFormsModule,
    IrhModule,
    EvdModule,
    AdmModule,
    SynchronizeModule,
    ApoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
