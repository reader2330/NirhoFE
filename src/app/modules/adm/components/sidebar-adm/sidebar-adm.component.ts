import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';
import {environment} from '../../../../../environments/environment';


@Component({
  selector: 'app-sidebar-adm',
  templateUrl: './sidebar-adm.component.html',
  styleUrls: ['./sidebar-adm.component.scss']
})
export class SidebarAdmComponent implements OnInit {

  mobile = false;
  selectModule = 1;
  modules = [{
    id_submodulo: 1,
    descripcion: 'Bandeja empleados',
  }, {
    id_submodulo: 2,
    descripcion: 'Alta de empleado'
  }];
  user = {};

  avatar = {
    url: ''
  };
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
  }*/

  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }

  }
  IrInicio() {
    this.route.navigate(['SYNC']);
  }

  cerraSesion() {
    this.LoginServices.closeSession().subscribe((res) => {
      console.log(res);
    });
    sessionStorage.clear();
    this.route.navigate(['']);
  }

  goAvatarEditing() {
    this.route.navigate(['avatar-edit']);
  }


  /*ngOnInit() {
    setTimeout(() => {
      this.getModules();
      this.getUser();
    }, 1500);
  }*/

  ngOnInit() {
  }

}
