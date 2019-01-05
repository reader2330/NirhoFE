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
  candidato = {
    username: '',
    password: '',
    rfc: ''
  };
  hasCandidato = false;
  newUser = false;
  constructor(private LoginService: LoginService, private router: Router, private route: ActivatedRoute, private Reclutamiento: ReclutamientoService) { }

  ngOnInit() {
    this.route.params.subscribe(res => {
      if (res.hasOwnProperty('Candidato')) {
        this.hasCandidato = true;
      }
      console.log(res);
    });
  }

  sendLogin() {
    if (!this.hasCandidato){
      this.LoginService.sendLogin(this.params).subscribe((res: HttpResponse<any>) => {
        console.log(res);
        if (res && res['token']) {
          localStorage.setItem('token', res['token']);

        }
        this.LoginService.getUser().subscribe(res2 => {
          if (res2['rol'] !== 4) {
            this.router.navigate(['SYNC']);
          } else {
            this.router.navigate(['configurador']);
          }
        });
      }, (err: HttpResponse<any>) => {

        console.log(err);
        Swal('Algo salio mal', 'Credenciales incorrectas', 'error');

      }, () => {
        console.log('acabe');
      });

    } else {
      this.sendLoginCandidato();
    }

  }
  showNewForm() {
    this.newUser = true;
  }
  generarUsuario() {
    console.log(this.candidato);
    this.Reclutamiento.saveCandidato(this.candidato).subscribe(res => {
      console.log(res);
      this.newUser = !this.newUser;
    });
  }

  sendLoginCandidato() {
    this.Reclutamiento.sendLogin(this.params).subscribe(res => {
      console.log(res);
      sessionStorage.setItem('Candidato', JSON.stringify(res));
      this.router.navigate(['RYS']);
    }, (err: HttpResponse<any>) => {

      console.log(err);
      Swal('Algo salio mal', 'Credenciales incorrectas', 'error');

    });
  }

}
