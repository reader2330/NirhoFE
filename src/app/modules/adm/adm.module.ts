import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioAdmComponent } from './components/inicio-adm/inicio-adm.component';
import { SidebarAdmComponent } from './components/sidebar-adm/sidebar-adm.component';
import {MaterialModule} from '../material/material.module';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { StepperAdmComponent } from './components/stepper-adm/stepper-adm.component';
import { EmployeeAdmComponent } from './components/employee-adm/employee-adm.component';
import { ContactAdmComponent } from './components/contact-adm/contact-adm.component';
import { PaymentAdmComponent } from './components/payment-adm/payment-adm.component';
import { ScholarshipAdmComponent } from './components/scholarship-adm/scholarship-adm.component';
import { LanguagesAdmComponent } from './components/languages-adm/languages-adm.component';
import { LanguageModalAdmComponent } from './components/language-modal-adm/language-modal-adm.component';
import { LaborAdmComponent } from './components/labor-adm/labor-adm.component';
import { LaborModalAdmComponent } from './components/labor-modal-adm/labor-modal-adm.component';


@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    //ChartModule.forRoot(required('highcharts')),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [
    InicioAdmComponent,
    SidebarAdmComponent,
    StepperAdmComponent,
    EmployeeAdmComponent,
    ContactAdmComponent,
    PaymentAdmComponent,
    ScholarshipAdmComponent,
    LanguagesAdmComponent,
    LanguageModalAdmComponent,
    LaborAdmComponent,
    LaborModalAdmComponent
  ],
  entryComponents: [
    LanguageModalAdmComponent,
    LaborModalAdmComponent,
    LaborAdmComponent
  ]
})
export class AdmModule { }
