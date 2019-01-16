import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './components/login/login.component';
import {InicioComponent} from './components/inicio/inicio.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {FormularioPrimaryComponent} from './components/formularios/formulario-primary/formulario-primary.component';
import {DataCompanyComponent} from './components/formularios/data-company/data-company.component';
import {DataProyectComponent} from './components/formularios/data-proyect/data-proyect.component';
import {DataPeriodComponent} from './components/formularios/data-period/data-period.component';
import {HeadCountComponent} from './components/formularios/head-count/head-count.component';
import {OrganigramaComponent} from './components/formularios/organigrama/organigrama.component';
import {SteppersComponent} from './components/formularios/steppers/steppers.component';
import {DataContactComponent} from './components/formularios/data-contact/data-contact.component';
import {BandejaComponent} from './components/bandeja/bandeja.component';
import {BandejaDetalleComponent} from './components/bandeja-detalle/bandeja-detalle.component';
import {CuestionarioSelectComponent} from './components/cuestionario-select/cuestionario-select.component';
import {AsignarConsultorComponent} from './components/asignar-consultor/asignar-consultor.component';
import {EstadisticasComponent} from './components/estadisticas/estadisticas.component';
import {DetallePreguntasComponent} from './components/detalle-preguntas/detalle-preguntas.component';
import {EncuestaComponent} from './components/encuesta/encuesta.component';
import {AvatarEditComponent} from './components/avatar-edit/avatar-edit.component';
import {MaterialModule} from '../material/material.module';
import {ChartModule} from 'angular2-highcharts';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '../../../../node_modules/@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PickerModule } from '@ctrl/ngx-emoji-mart';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { EmojiModule } from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {LoginInterceptor} from './services/login.interceptor';
import {GeneradorReportesComponent} from '../shared/components/generador-reportes/generador-reportes.component';
import {SharedModule} from '../shared/shared.module';
import {ImagenesModalComponent} from '../modal/imagenes-modal/imagenes-modal.component';
import {AvisoComponent} from './components/aviso/aviso.component';
declare var require: any;

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
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    EmojiModule,
    PickerModule,
    SharedModule

  ],
  declarations: [
    LoginComponent,
    InicioComponent,
    SidebarComponent,
    FormularioPrimaryComponent,
    DataCompanyComponent,
    DataProyectComponent,
    DataPeriodComponent,
    HeadCountComponent,
    OrganigramaComponent,
    SteppersComponent,
    DataContactComponent,
    BandejaComponent,
    BandejaDetalleComponent,
    CuestionarioSelectComponent,
    AsignarConsultorComponent,
    EstadisticasComponent,
    DetallePreguntasComponent,
    EncuestaComponent,
    AvatarEditComponent,
    AvisoComponent
  ],
  entryComponents: [
    ImagenesModalComponent,
    AvisoComponent
  ],
  exports: [
    CuestionarioSelectComponent
  ],
  providers: [
    {
      provide: HighchartsStatic,
      useFactory: highchartsFactory
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoginInterceptor,
      multi: true
    }
  ],
})
export class CLBModule { }
