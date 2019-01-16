import { BrowserModule } from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { registerLocaleData } from '@angular/common';
import localeMx from '@angular/common/locales/es-MX';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './modules/material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {IrhModule} from './modules/irh/irh.module';
import {CLBModule} from './modules/clb/clb.module';
import {EVAModule} from './modules/eva/eva.module';
import {EvdModule} from './modules/evd/evd.module';
import {AdmModule} from './modules/adm/adm.module';
import {SynchronizeModule} from './modules/synchronize/synchronize.module';
import {ApoModule} from './modules/apo/apo.module';
import {MAT_DATE_LOCALE} from '@angular/material';

import { ImagenesModalComponent } from './modules/modal/imagenes-modal/imagenes-modal.component';
import {ViewActividadesComponent} from './screensOut/view-actividades/view-actividades.component';
import {VerticalTimelineModule} from 'angular-vertical-timeline';
import {PvcModule} from './modules/pvc/pvc.module';
import {ChartModule} from 'angular2-highcharts';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import { VistaPVCParticipanteComponent } from './screensOut/vista-pvcparticipante/vista-pvcparticipante.component';
import {ModalUsuariosComponent} from './modules/modal/modal-usuarios/modal-usuarios.component';
import {RYSModule} from './modules/rys/rys.module';




registerLocaleData(localeMx);

export function highchartsFactory() {
  const hc = require('highcharts');
  const h3 = require('highcharts/highcharts-3d');
  h3(hc);

  return hc;
}



@NgModule({
  declarations: [
    AppComponent,
    ImagenesModalComponent,
    ViewActividadesComponent,
    VistaPVCParticipanteComponent,
    ModalUsuariosComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    ChartModule,
    CLBModule,
    EVAModule,
    FormsModule,
    ReactiveFormsModule,
    IrhModule,
    EvdModule,
    AdmModule,
    SynchronizeModule,
    ApoModule,
    PvcModule,
    VerticalTimelineModule,
    RYSModule
  ],
  entryComponents: [
    ImagenesModalComponent
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'es-MX'},
    { provide: LOCALE_ID, useValue: 'es-MX' },
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {


}
