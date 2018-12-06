import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-apo',
  templateUrl: './sidebar-apo.component.html',
  styleUrls: ['./sidebar-apo.component.scss']
})
export class SidebarApoComponent implements OnInit {

  mobile = false;
  selectModule = 2;
  modules = [];
  user = {};
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

  goModule(opt) {
    this.selectModule = opt;
  }

  getModules() {
    this.modules = [{
      id_submodulo: 2,
      descripcion: 'Bandeja de proyectos'
    }, {
      id_submodulo: 3,
      descripcion: 'Alta proyecto'
    }, {
      id_submodulo: 4,
      descripcion: 'Head Count'
    }, {
      id_submodulo: 5,
      descripcion: 'Head Count Ampliado'
    }, {
      id_submodulo: 6,
      descripcion: 'Periodo de garantía'
    }, {
      id_submodulo: 7,
      descripcion: 'Otro'
    }];
    /*this.loginService.getModules().subscribe((res) => {
    });*/
  }

  ngOnInit() {
    setTimeout(() => {
      this.getModules();
      this.getUser();
    }, 1500);
  }

  goAvatarEditing() {
    this.route.navigate(['avatar-edit']);
  }

  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }
  }
  getUser() {
    this.LoginService.getUser().subscribe((res) => {
      this.user = res;
      console.log(res);
      sessionStorage.setItem('user', JSON.stringify(this.user));
      if (this.user) {
        this.avatar.url = this.user['avatar'];
      }
    });
  }


  cerraSesion() {
    sessionStorage.clear();
    localStorage.clear();
    this.route.navigate(['']);
  }
  IrInicio() {
    this.route.navigate(['SYNC']);
  }

}
