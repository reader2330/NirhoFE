import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../clb/services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-evd',
  templateUrl: './login-evd.component.html',
  styleUrls: ['./login-evd.component.scss']
})
export class LoginEvdComponent implements OnInit {

  params = {
    username: '',
    password: '',
  };

  constructor(private LoginServices: LoginService, private router: Router) { }

  sendLogin() {
    this.LoginServices.sendLogin(this.params).subscribe((res) =>{
      console.log(res);
    }, (err) => {
      if (err) console.log(err);

    }, () => {
      console.log('acabe');
    });
    this.router.navigate(['inicio']);
  }

  ngOnInit() {
  }

}
