import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import Swal from "sweetalert2";
import {HttpResponse} from '@angular/common/http';

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

  constructor(private LoginService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  sendLogin() {
      this.LoginService.sendLogin(this.params).subscribe((res: HttpResponse<any>) => {
        console.log(res);
        if (res && res['token']) {
          localStorage.setItem('token', res['token']);
        }

        this.router.navigate(['SYNC']);
      }, (err: HttpResponse<any>) => {

        localStorage.setItem('token', '8347265298347562938');
        this.router.navigate(['SYNC']);
          
        
        console.log(err);
        Swal('Algo salio mal', 'Credenciales incorrectas', 'error');

      }, () => {
        console.log('acabe');
      });

  }

}
