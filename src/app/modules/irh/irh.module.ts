import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnterpriseListComponent } from './components/enterprise-list/enterprise-list.component';
import {AppRoutingModule} from '../../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material/material.module';
import { SidebarIrhComponent } from './components/sidebar-irh/sidebar-irh.component';
import { LoginIrhComponent } from './components/login-irh/login-irh.component';
import { InicioIrhComponent } from './components/inicio-irh/inicio-irh.component';
import { EnterpriseIrhComponent } from './components/enterprise-irh/enterprise-irh.component';
import { RecruitingIrhComponent } from './components/reviews/recruiting-irh/recruiting-irh.component';
import { RecruitingModalIrhComponent } from './components/reviews/modals/recruiting-modal-irh/recruiting-modal-irh.component';
import { RhAdminModalIrhComponent } from './components/reviews/modals/rh-admin-modal-irh/rh-admin-modal-irh.component';
import { RhAdminIrhComponent } from './components/reviews/rh-admin-irh/rh-admin-irh.component';
import { OrganizationalDevelopmentIrhComponent } from './components/reviews/organizational-development-irh/organizational-development-irh.component';
import { OrganizationalDevelopmentModalIrhComponent } from './components/reviews/modals/organizational-development-modal-irh/organizational-development-modal-irh.component';
import { TrainingIrhComponent } from './components/reviews/training-irh/training-irh.component';
import { TrainingModalIrhComponent } from './components/reviews/modals/training-modal-irh/training-modal-irh.component';
import { EnterpriseDetailIrhComponent } from './components/enterprise-detail-irh/enterprise-detail-irh.component';
import {CuestionarioSelectIRHComponent} from './components/cuestionario-select-irh/cuestionario-select-irh.component';
import {EncuestaIrhComponent} from './components/encuesta_irh/encuesta-irh.component';
import { PickerModule } from '@ctrl/ngx-emoji-mart';
import { EmojiModule } from '@ctrl/ngx-emoji-mart/ngx-emoji';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    EmojiModule,
    PickerModule
  ],
  declarations: [
    EnterpriseListComponent,
    SidebarIrhComponent,
    LoginIrhComponent,
    InicioIrhComponent,
    EnterpriseIrhComponent,
    RecruitingIrhComponent,
    RecruitingModalIrhComponent,
    RhAdminModalIrhComponent,
    RhAdminIrhComponent,
    OrganizationalDevelopmentIrhComponent,
    OrganizationalDevelopmentModalIrhComponent,
    TrainingIrhComponent,
    TrainingModalIrhComponent,
    EnterpriseDetailIrhComponent,
    CuestionarioSelectIRHComponent,
    EncuestaIrhComponent
  ],
  entryComponents: [
    RecruitingModalIrhComponent,
    RhAdminModalIrhComponent,
    OrganizationalDevelopmentModalIrhComponent,
    RhAdminModalIrhComponent,
    TrainingModalIrhComponent
  ]
})
export class IrhModule { }
