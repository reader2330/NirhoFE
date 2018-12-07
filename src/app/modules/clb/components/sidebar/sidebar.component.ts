import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';
import {environment} from '../../../../../environments/environment';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  mobile = false;
  selectModule = 1;
  modules = [];
  user = {
    fullName: ''
  };
  avatar = {
    url: ''
  };
  constructor(breakpointObserver: BreakpointObserver, private route: Router, private LoginService: LoginService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
      } else {
        this.mobile = false;
      }
    });

  }




  ngOnInit() {
    setTimeout(() => {
      this.getModules();
      this.getUser();
    }, 1500);
  }

  goModule(opt) {
    this.selectModule = opt;
  }
  getModules() {
    this.LoginService.getModules().subscribe((res) => {
      this.modules = res;
    });
  }
  getUser() {
    this.LoginService.getUser().subscribe((res) => {
      this.user = res;
      sessionStorage.setItem('user', JSON.stringify(this.user));
      if (this.user) {
        this.avatar.url = this.user['avatar'];
      }
    });
  }
  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }

  }

  cerraSesion() {
    this.LoginService.closeSession().subscribe((res) => {
      console.log(res);
    });
    localStorage.clear();
    sessionStorage.clear();
    this.route.navigate(['']);
  }

  IrInicio() {
    this.route.navigate(['SYNC']);
  }

  goAvatarEditing() {
    this.route.navigate(['avatar-edit', 'CLB']);
  }

}
