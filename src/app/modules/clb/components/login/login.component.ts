import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from "sweetalert2";
import {HttpResponse} from '@angular/common/http';
import {ReclutamientoService} from '../../../rys/services/reclutamiento.service';
import {connectableObservableDescriptor} from 'rxjs/internal/observable/ConnectableObservable';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  params = {
    username: '',
    password: '',
  };
  typeUser = 0;
  candidato = {
    username: '',
    password: '',
    rfc: '',
    email: ''
  };
  options = [
    {
      id: 1,
      name: 'Candidato'
    },
    {
      id: 2,
      name: 'Empresa'
    }
  ];
  newUser = false;
  constructor(private LoginService: LoginService, private router: Router, private Reclutamiento: ReclutamientoService) { }
  ngOnInit() {
  }

  sendLogin() {
      this.LoginService.sendLogin(this.params).subscribe((res: HttpResponse<any>) => {
        console.log(res);
        if (res && res['token']) {
          localStorage.setItem('token', res['token']);
        }
        this.LoginService.getUser().subscribe(res2 => {
          if (res2['rol'] < 4) {
            this.router.navigate(['SYNC']);
          } else {
            switch (res2['rol']) {
              case 4:
                this.router.navigate(['configurador']);
                break;
              case 5:
                this.router.navigate(['RYS']);
                break;
              case 6:
                this.router.navigate(['RYS']);
                break;
            }
          }
        });
      }, (err: HttpResponse<any>) => {
        Swal('Algo salio mal', 'Credenciales incorrectas', 'error');

      });
  }


  showNewForm() {
    this.newUser = true;
  }
  generarUsuario() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar los datos',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {

        switch (this.typeUser) {
          case 1:
            this.crearCandidato();
            break;
          case 2:
            this.crearSolicitante();
            break;
        }
      }
    });
  }

  crearCandidato() {
    this.Reclutamiento.saveCandidato(this.candidato).subscribe(res => {
      Swal(
        'Listo.',
        'La información se guardo correctamente',
        'success'
      ).then(() => {
        this.newUser = !this.newUser;
      });
    });
  }
  crearSolicitante() {
    this.Reclutamiento.saveSolicitante(this.candidato).subscribe(res => {
      Swal(
        'Listo.',
        'La información se guardo correctamente',
        'success'
      ).then(() => {
        this.newUser = !this.newUser;
      });
    });
  }
}
