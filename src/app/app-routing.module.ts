import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './modules/clb/components/login/login.component';
import { InicioComponent } from './modules/clb/components/inicio/inicio.component';
import { AvatarEditComponent } from './modules/clb/components/avatar-edit/avatar-edit.component';
import { EncuestaComponent } from './modules/clb/components/encuesta/encuesta.component';
import { Sidebar360Component } from './modules/eva/components/sidebar-360/sidebar-360.component';

import {InicioIrhComponent} from './modules/irh/components/inicio-irh/inicio-irh.component';
import {InicioEvdComponent} from './modules/evd/components/inicio-evd/inicio-evd.component';
import {InicioAdmComponent} from './modules/adm/components/inicio-adm/inicio-adm.component';
import {InicioSyncComponent} from './modules/synchronize/components/inicio-sync/inicio-sync.component';
import {InicioApoComponent} from './modules/apo/components/inicio-apo/inicio-apo.component';

import {LoginGuard} from './guards/login.guard';
import {EncuestaEvdComponent} from './modules/evd/components/encuesta-evd/encuesta-evd.component';
import {EncuestaEva360Component} from './modules/eva/components/encuesta-eva360/encuesta-eva360.component';
import {ViewActividadesComponent} from './screensOut/view-actividades/view-actividades.component';
import {SidebarPvcComponent} from './modules/pvc/components/sidebar-pvc/sidebar-pvc.component';
import {VistaPVCParticipanteComponent} from './screensOut/vista-pvcparticipante/vista-pvcparticipante.component';
import {ConfiguradorClienteComponent} from './modules/synchronize/components/configurador-cliente/configurador-cliente.component';
import {InformacionFormComponent} from './modules/rys/informacion-form/informacion-form.component';
import {VacanteTableComponent} from './modules/rys/vacante-table/vacante-table.component';
import {SidebarRysComponent} from './modules/rys/sidebar-rys/sidebar-rys.component';



const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'Login/:Candidato',
    component: LoginComponent
  },
  {
    path: 'CLB',
    component: InicioComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'avatar-edit/:module',
    component: AvatarEditComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'encuesta/:token',
    component: EncuestaComponent,
    //canActivate: [LoginGuard]
  },
  {
    path: 'EVA360',
    component: Sidebar360Component,
    canActivate: [LoginGuard]
  }, {
    path: 'IRH',
    component: InicioIrhComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'EVD',
    component: InicioEvdComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'ADM',
    component: InicioAdmComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'SYNC',
    component: InicioSyncComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'APO',
    component: InicioApoComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'encuesta/EVD/:token',
    component: EncuestaEvdComponent
  },
  {
    path: 'encuesta/EVA360/:token',
    component: EncuestaEva360Component
  },
  {
    path: 'actividades/participante/:token',
    component: ViewActividadesComponent
  },
  {
    path: 'PVC',
    component: SidebarPvcComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'seguimiento/participante/:token',
    component: VistaPVCParticipanteComponent
  },
  {
    path: 'configurador',
    component: ConfiguradorClienteComponent
  },
  {
    path: 'RYS',
    component: SidebarRysComponent
  },

  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
