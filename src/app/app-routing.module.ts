import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './modules/clb/components/login/login.component';
import {InicioComponent} from './modules/clb/components/inicio/inicio.component';
import {AvatarEditComponent} from './modules/clb/components/avatar-edit/avatar-edit.component';
import {EncuestaComponent} from './modules/clb/components/encuesta/encuesta.component';
import {InicioIrhComponent} from './modules/irh/components/inicio-irh/inicio-irh.component';
import {InicioEvdComponent} from './modules/evd/components/inicio-evd/inicio-evd.component';
import {InicioAdmComponent} from './modules/adm/components/inicio-adm/inicio-adm.component';
import {InicioSyncComponent} from './modules/synchronize/components/inicio-sync/inicio-sync.component';
import {InicioApoComponent} from './modules/apo/components/inicio-apo/inicio-apo.component';

import {LoginGuard} from './guards/login.guard';


const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'CLB',
    component: InicioComponent,
    //canActivate: [LoginGuard]
  },
  {
    path: 'avatar-edit/:module',
    component: AvatarEditComponent,
    //canActivate: [LoginGuard]
  },
  {
    path: 'encuesta/:token',
    component: EncuestaComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'IRH',
    component: InicioIrhComponent,
    //canActivate: [LoginGuard]
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
    component: InicioSyncComponent
  },
  {
    path: 'APO',
    component: InicioApoComponent
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
