import { Component, OnInit } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import {Router} from '@angular/router';
import {environment} from '../../../../../environments/environment';
import {LoginService} from '../../../clb/services/login.service';
import {ClienteServiceService} from '../../services/cliente-service.service';


@Component({
  selector: 'app-routing-sync',
  templateUrl: './routing-sync.component.html',
  styleUrls: ['./routing-sync.component.scss']
})
export class RoutingSyncComponent implements OnInit {

  adm = false;
  imagenes = [{
    url: environment.urlNG + 'assets/imagen1.jpeg'
  },
    {
      url: environment.urlNG + 'assets/imagen2.jpeg'
    },
    {
      url: environment.urlNG + 'assets/imagen3.jpeg'
    },
    {
      url: environment.urlNG + 'assets/imagen4.jpeg'
    },
  ];
  modules = [];
  user = {};

  constructor(private router: Router, private LoginService: LoginService, private ClienteServices: ClienteServiceService) { }

  goModule(module) {
    console.log(module);
    switch (module) {
      case 'Clima laboral': {
        this.router.navigate(['CLB']);
        sessionStorage.setItem('moduleActive', 'CLB');
        break;
      }
      case 'Ingeniería de Recursos Humanos': {
        this.router.navigate(['IRH']);
        sessionStorage.setItem('moduleActive', 'IRH');
        break;
      }
      case 'Nómina': {
        this.router.navigate(['ADM']);
        sessionStorage.setItem('moduleActive', 'ADM');
        break;
      }
      case 'Evaluación del Desempeño': {
        this.router.navigate(['EVD']);
        sessionStorage.setItem('moduleActive', 'EVD');
        break;
      }
      case 'Evaluación del Desempeño 360': {
        this.router.navigate(['EVA360']);
        sessionStorage.setItem('moduleActive', 'EVA360');
        break;
      }
      case 'Administración por Objetivos': {
        this.router.navigate(['APO']);
        sessionStorage.setItem('moduleActive', 'APO');
        break;
      }
      case 'Plan de Vida y  Carrera': {
        this.router.navigate(['PVC']);
        sessionStorage.setItem('moduleActive', 'PVC');
        break;
      }
      case 'Reclutamiento y Selección de Personal': {
        this.router.navigate(['RYS']);
        sessionStorage.setItem('moduleActive', 'RYS');
        break;
      }
      case 'CRM': {
        window.location.href = 'https://www.salesup.com/login/';
        break;
      }
      case 'Administración': {
        window.location.href = 'https://cfd.sicofi.com.mx/Sicofi/Main';
        break;
      }
    }

  }
  ngOnInit() {
    this.LoginService.getUser().subscribe(res => {
      this.user = res;
      if (res && res.rol !== 2 ) {
        this.adm = false;
      } else {
        if(res.rol === 2) {
          this.adm = true;
        }
      }
      this.ClienteServices.getModules().subscribe(res2 => {
        console.log(res2);
        this.modules = res2;
        if (!this.adm) {
            this.modules.map((item, i) => {
              if (item.nombre === 'Nómina') {
                this.modules.splice(i, 1);
              }
            });
        }
        this.modules.push({
            id: 10,
            nombre: 'CRM'
          },
          {
            id: 11,
            nombre: 'Administración'
          });

      });
    });
  }

  StateForUser() {
    return this.user['rol'] !== 1;
  }

}
