import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';

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

  constructor(private LoginService:LoginService, private router:Router) { }

  ngOnInit() {
  }

  sendLogin(){
      this.LoginService.sendLogin(this.params).subscribe((res) =>{
        console.log(res);
      }, (err) => {
        if(err) console.log(err);

      }, () => {
        console.log("acabe")
      });
    this.router.navigate(['inicio'])
  }

}
