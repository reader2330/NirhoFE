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
  selectedItem = 1;
  selectModule = 1;
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
    if (sessionStorage.getItem('Candidato')) {
      this.candidato = sessionStorage.getItem('Candidato');
      this.ModulesCandidato();
    } else {
      this.getUser();

      setTimeout(() => {
        this.getModules();
      }, 1500);
    }
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
          id_submodulo: 6,
          descripcion: 'Estadisticas'
        }
      ];
    }
    if (this.user['rol'] === 3) {
      this.ModulesConsultor();
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
        }
      ];
  }

  ModulesCandidato() {
    this.modules = [
      {
        id_submodulo: 1,
        descripcion: 'Bandeja de vacantes'
      },
      {
        id_submodulo: 4,
        descripcion: 'Información candidato'
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
