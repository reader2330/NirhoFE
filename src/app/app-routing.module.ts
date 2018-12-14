import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './modules/clb/components/login/login.component';
import { InicioComponent } from './modules/clb/components/inicio/inicio.component';
import { SteppersComponent } from './modules/clb/components/formularios/steppers/steppers.component';
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



const routes: Routes = [
  {
    path: '',
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
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
