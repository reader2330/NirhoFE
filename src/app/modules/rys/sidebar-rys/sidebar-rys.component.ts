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
import {LoginService} from '../../clb/services/login.service';


@Component({
  selector: 'app-sidebar-rys',
  templateUrl: './sidebar-rys.component.html',
  styleUrls: ['./sidebar-rys.component.scss']
})
export class SidebarRysComponent implements OnInit {
  mobile = false;
  showButtonHome = false;
  selectedItem = 0;
  selectModule = 0;
  modules = [];
  user = {};
  candidato = {};
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
    if (this.user['rol'] === 1) {
      this.modules = [
        {
          id_submodulo: 1,
          descripcion: 'Bandeja de vacantes'
        },
        {
          id_submodulo: 2,
          descripcion: 'Alta de vacante'
        },
      ];
      this.showButtonHome = true;
    }
    if ( this.user['rol'] === 2) {
      this.modules = [
        {
          id_submodulo: 1,
          descripcion: 'Bandeja de vacantes'
        },
        {
          id_submodulo: 2,
          descripcion: 'Alta de vacante'
        },
        {
          id_submodulo: 4,
          descripcion: 'Información candidato'
        },
        {
          id_submodulo: 10,
          descripcion : 'Periodo de garantia'
        },
        {
          id_submodulo: 11,
          descripcion : 'Asignar consultor'
        },
        {
          id_submodulo: 6,
          descripcion: 'Estadisticas'
        }
      ];
      this.showButtonHome = true;
    }
    if (this.user['rol'] === 3) {
      this.ModulesConsultor();
      this.showButtonHome = true;
      sessionStorage.setItem('candidatoActive', '');
    }
    if (this.user['rol'] === 5) {
      this.ModulesCandidato();
      this.showButtonHome = false;
      sessionStorage.setItem('candidatoActive', this.user['fullName']);
    }
    if (this.user['rol'] === 6 ) {
      this.ModulesSolicitante();
      this.showButtonHome = false;
    }

  }

  ModulesConsultor() {
    this.modules =
      [
        {
          id_submodulo: 1,
          descripcion: 'Bandeja de vacantes'
        },
        {
          id_submodulo: 7,
          descripcion: 'Bandeja de candidatos'
        },
        {
          id_submodulo: 2,
          descripcion: 'Alta de vacante'
        },
        {
          id_submodulo: 4,
          descripcion: 'Información candidato'
        },
        {
          id_submodulo: 5,
          descripcion: 'Generar reportes'
        },
        {
          id_submodulo: 9,
          descripcion: 'Subir documentos'
        }
      ];
  }

  ModulesCandidato() {
    this.modules = [
      {
        id_submodulo: 1,
        descripcion: 'Bandeja de entrevistas'
      },
      {
        id_submodulo: 4,
        descripcion: 'Información candidato'
      },
      {
        id_submodulo: 9,
        descripcion: 'Subir documentos'
      }
    ];

  }

  ModulesSolicitante() {
    this.selectedItem = 8;
    this.selectModule = 8;
    this.modules = [
      {
        id_submodulo: 8,
        descripcion: 'Bandeja de vacantes'
      },
      {
        id_submodulo: 2,
        descripcion: 'Información solicitante'
      },
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
      this.selectedItem = evt.value;
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
    this.route.navigate(['avatar-edit', 'RYS']);
  }

}
