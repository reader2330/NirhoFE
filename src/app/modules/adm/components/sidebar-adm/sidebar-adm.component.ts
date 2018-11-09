import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-adm',
  templateUrl: './sidebar-adm.component.html',
  styleUrls: ['./sidebar-adm.component.scss']
})
export class SidebarAdmComponent implements OnInit {

  mobile = false;
  selectModule = 1;
  modules = [];
  user = {};

  constructor(breakpointObserver: BreakpointObserver, private route: Router, private LoginServices: LoginService) {
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

  goModule(opt) {
    this.selectModule = opt;
  }
  /*getModules() {
    this.LoginServices.getModules().subscribe((res) => {
      this.modules = res;
    });
  }

  getUser() {
    this.LoginServices.getUser().subscribe((res) => {
      this.user = res;
      sessionStorage.setItem('user', JSON.stringify(this.user));
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
    sessionStorage.clear();
    this.route.navigate(['']);
  }*/


  /*ngOnInit() {
    setTimeout(() => {
      this.getModules();
      this.getUser();
    }, 1500);
  }*/

  ngOnInit() {
  }

}
