import {Component, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-evd',
  templateUrl: './sidebar-evd.component.html',
  styleUrls: ['./sidebar-evd.component.scss']
})
export class SidebarEvdComponent implements OnInit {

  mobile = false;
  selectedItem = 2;
  selectModule = 2;
  modules = [];
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

  ngOnInit() {
    setTimeout(() => {
      this.getModules();
      this.getUser();
    }, 1500);
  }

  goModule(opt) {
    this.selectedItem = opt;
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
      descripcion: 'Organigrama'
    }, {
      id_submodulo: 6,
      descripcion: 'Ver cuestionario'
    }, {
      id_submodulo: 7,
      descripcion: 'Estado del cuestionario'
    }];
    /*this.loginService.getModules().subscribe((res) => {
    });*/
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

  /*cerraSesion() {
    this.LoginService.closeSession().subscribe((res) => {
      console.log(res);
    });
    sessionStorage.clear();
    this.route.navigate(['']);
  }*/

  goAvatarEditing() {
    this.route.navigate(['avatar-edit']);
  }

  IrInicio() {
    this.route.navigate(['SYNC']);
  }
}
