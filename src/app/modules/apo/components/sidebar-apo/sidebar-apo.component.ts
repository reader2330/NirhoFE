import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-sidebar-apo',
  templateUrl: './sidebar-apo.component.html',
  styleUrls: ['./sidebar-apo.component.scss']
})
export class SidebarApoComponent implements OnInit {

  mobile = false;
  selectModule = 1;
  modules = [];
  user = {
    fullName: '',
    rol: 0
  };
  avatar = {
    url: ''
  };

  constructor(breakpointObserver: BreakpointObserver, private route: Router, private LoginService: LoginService , private ProyectoServicesAPO: ProyectoApoService) {
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
    this.ProyectoServicesAPO.getModules(this.user.rol).subscribe((res) => {
      console.log(res);
      for (let obj of res) {
        this.modules.push({id_submodulo: obj.idSubmodulo, descripcion: obj.descripcion});
      }
      console.log(this.modules);
    });
  }
  getFrecuencia(num) {

    if (num === 1) {
      return 'Mensual';
    }
    if (num === 2) {
      return 'Bimestral';
    }
    if (num === 3) {
      return 'Semestral';
    }
    if (num === 4) {
      return 'Anual';
    }
    if (num === 5 ) {
      return 'Unica vez';
    }

  }

  ngOnInit() {
    this.getUser();
    setTimeout(() => {

      this.getModules();

    }, 1500);
  }

  goAvatarEditing() {
    this.route.navigate(['avatar-edit', 'APO']);
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
