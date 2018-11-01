import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {InicioComponent} from './components/inicio/inicio.component';
import {SteppersComponent} from './components/formularios/steppers/steppers.component';
import {AvatarEditComponent} from './components/avatar-edit/avatar-edit.component';
import {EncuestaComponent} from './components/encuesta/encuesta.component';

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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
