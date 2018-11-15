import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './modules/clb/components/login/login.component';
import {InicioComponent} from './modules/clb/components/inicio/inicio.component';
import {SteppersComponent} from './modules/clb/components/formularios/steppers/steppers.component';
import {AvatarEditComponent} from './modules/clb/components/avatar-edit/avatar-edit.component';
import {EncuestaComponent} from './modules/clb/components/encuesta/encuesta.component';
import {InicioIrhComponent} from './modules/irh/components/inicio-irh/inicio-irh.component';
import {InicioEvdComponent} from './modules/evd/components/inicio-evd/inicio-evd.component';
import {InicioAdmComponent} from './modules/adm/components/inicio-adm/inicio-adm.component';
import {InicioSyncComponent} from './modules/synchronize/components/inicio-sync/inicio-sync.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'inicio',
    component: InicioComponent
  },
  {
    path: 'avatar-edit',
    component: AvatarEditComponent
  },
  {
    path: 'encuesta/:token',
    component: EncuestaComponent
  },
  {
    path: 'IRH',
    component: InicioIrhComponent
  },
  {
    path: 'EVD',
    component: InicioEvdComponent
  },
  {
    path: 'ADM',
    component: InicioAdmComponent
  },
  {
    path: 'SYNC',
    component: InicioSyncComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
