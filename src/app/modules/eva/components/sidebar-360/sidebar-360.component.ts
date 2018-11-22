import {
  Component,
  OnInit
} from '@angular/core';
import {
  BreakpointObserver,
  Breakpoints
} from '@angular/cdk/layout';
import {
  Router
} from '@angular/router';
import {
  LoginService
} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-360',
  templateUrl: './sidebar-360.component.html',
  styleUrls: ['./sidebar-360.component.scss']
})
export class Sidebar360Component implements OnInit {
  mobile = false;
  selectModule = 1;
  modules = [];
  user = {};
  constructor(breakpointObserver: BreakpointObserver, private route: Router, private loginService: LoginService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait
    ]).subscribe(result => {
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
    this.modules = [{
      id_submodulo: 2,
      descripcion: 'Revisión de proyecto'
    }, {
      id_submodulo: 3,
      descripcion: 'Configuración y carga de Head Count'
    }, {
      id_submodulo: 4,
      descripcion: 'Configuración y elaboración de cuestionario'
    }, {
      id_submodulo: 5,
      descripcion: 'Analisis de información'
    }, {
      id_submodulo: 6,
      descripcion: 'Integración de informes'
    }];
    this.loginService.getModules().subscribe((res) => {
    });
  }
  getUser() {
    this.loginService.getUser().subscribe((res) => {
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
    this.loginService.closeSession().subscribe((res) => {
      console.log(res);
    });
    sessionStorage.clear();
    this.route.navigate(['']);
  }

}
