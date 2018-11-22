import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';


@Component({
  selector: 'app-sidebar-irh',
  templateUrl: './sidebar-irh.component.html',
  styleUrls: ['./sidebar-irh.component.scss']
})
export class SidebarIrhComponent implements OnInit {

  mobile = false;
  selectModule = 0;
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

  ngOnInit() {
    this.modules.push({
      id_submodulo: 1,
      descripcion: 'Bandeja de empresas'
    }, {
      id_submodulo: 2,
      descripcion: 'Alta de empresa'
    }, {
      id_submodulo: 3,
      descripcion: 'Configurar cuestionario'
    }, {
       id_submodulo: 4,
        descripcion: 'Contestar preguntas'
    })

    this.getUser();
  }

  goModule(opt) {
    if (opt == 2) {
      sessionStorage.clear();
    }
    this.selectModule = opt;
  }
  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }
  }
  cerrarSesion(){
    sessionStorage.clear();
    this.route.navigate(['/']);
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
  goAvatarEditing() {
    this.route.navigate(['avatar-edit']);
  }

}
