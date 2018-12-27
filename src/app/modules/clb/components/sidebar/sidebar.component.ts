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
    console.log("hola");
    this.getUser();
    setTimeout(() => {
      this.getModules();
    }, 1500);
  }

  goModule(opt) {
    this.selectModule = opt;
  }
  getModules() {
    this.LoginService.getModules().subscribe((res) => {
      this.modules = res;
      for (let mod of this.modules) {
        if (mod['id_submodulo'] === 8){
          mod.descripcion = "Enviar cuestionarios";
        }
        if (mod['id_submodulo'] === 9) {
          mod.descripcion = "Graficas de resultados";
        }
      }
      if (this.user['rol'] === 3) {
        this.modules.push({
          id_submodulo: 11,
          descripcion: 'Generar reportes'
        });
      }
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
      this.mobile = false;
    }
    console.log(evt.key);
    if (evt.key) {
      this.mobile = true;
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
