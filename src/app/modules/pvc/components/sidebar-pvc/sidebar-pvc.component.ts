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
  templateUrl: './sidebar-pvc.component.html',
  styleUrls: ['./sidebar-pvc.component.scss']
})
export class SidebarPvcComponent implements OnInit {
  mobile = false;
  selectedItem = 1;
  selectModule = 1;
  modules = [];
  user = {};
  avatar = {
    url: ''
  };
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
    this.getUser();
    setTimeout(() => {
      this.getModules();
    }, 1500);
  }

  goModule(opt) {
    this.selectedItem = opt;
    this.selectModule = opt;
  }
  getModules() {
    if (this.user['rol'] !== 3 ) {
      this.loginService.getModules().subscribe((res) => {
        console.log(res);
        this.modules = res;
        if (this.user['rol'] === 3) {
        }
      });
    } else {
      this.ModulesConsultor();
    }

  }

  ModulesConsultor(){
    this.modules =
      [
        {
          id_submodulo: 1,
          descripcion: 'Bandeja de proyectos'
        },
        {
          id_submodulo: 11,
          descripcion: 'Configurar areas'
        },
        {
          id_submodulo: 12,
          descripcion: 'Configurar esferas'
        },
        {
          id_submodulo: 13,
          descripcion: 'Configurar niveles'
        },
        {
          id_submodulo: 14,
          descripcion: 'Configurar especialidades'
        },
        {
          id_submodulo: 15,
          descripcion: 'Configurar conocimientos tecnicos'
        },
        {
          id_submodulo: 16,
          descripcion: 'Configurar conocimientos humanos'
        },
        {
          id_submodulo: 17,
          descripcion: 'Carga de Head Count'
        },
        {
          id_submodulo: 18,
          descripcion: 'Envio de correos'
        },
        {
          id_submodulo: 19,
          descripcion: 'Generar reportes'
        }

      ];
  }

  getUser() {
    this.loginService.getUser().subscribe((res) => {
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
    this.loginService.closeSession().subscribe((res) => {
      console.log(res);
    });
    sessionStorage.clear();
    localStorage.clear();
    this.route.navigate(['']);
  }
  IrInicio() {
    this.route.navigate(['SYNC']);
  }
  goAvatarEditing() {
    this.route.navigate(['avatar-edit', 'EVA360']);
  }

}
