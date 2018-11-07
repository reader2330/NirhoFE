import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './modules/clb/components/login/login.component';
import {InicioComponent} from './modules/clb/components/inicio/inicio.component';
import {SteppersComponent} from './modules/clb/components/formularios/steppers/steppers.component';
import {AvatarEditComponent} from './modules/clb/components/avatar-edit/avatar-edit.component';
import {EncuestaComponent} from './modules/clb/components/encuesta/encuesta.component';
import {InicioIrhComponent} from './modules/irh/components/inicio-irh/inicio-irh.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
