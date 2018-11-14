import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import Swal from "sweetalert2";

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

  sendLogin(){
      this.LoginService.sendLogin(this.params).subscribe((res) =>{
        console.log(res);
        this.router.navigate(['ADM']);
      }, (err) => {
        Swal('Algo salio mal', 'Credenciales incorrectas', 'error');

      }, () => {
        console.log('acabe');
      });

  }

}
