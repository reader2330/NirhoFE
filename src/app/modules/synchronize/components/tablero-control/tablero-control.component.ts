import { Component, OnInit } from '@angular/core';
import {ClienteServiceService} from '../../services/cliente-service.service';
import {LoginService} from '../../../clb/services/login.service';

@Component({
  selector: 'app-tablero-control',
  templateUrl: './tablero-control.component.html',
  styleUrls: ['./tablero-control.component.scss']
})
export class TableroControlComponent implements OnInit {
  showTable = false;
  displayedColumns = ['nombre', 'empresa', 'estatus', 'modulo'];
  dataSource = [{
    nombre: 'Hola',
    empresa: 'Hola',
    estatus: 'Hola',
    modulo: 'Clima laboral'
  }];
  user = {};

  constructor(private ClienteServices: ClienteServiceService, private LoginServices: LoginService) { }

  ngOnInit() {
    this.LoginServices.getUser().subscribe(res => {
      this.user = res;
      if (this.user['rol'] !== 2) {
        this.getProyects();
      } else {
        this.getProyectsGerente();
      }


    });
  }

  getProyects() {
      this.ClienteServices.getProyectsTablero(this.user['id']).subscribe(res => {
        this.dataSource = res;
        this.showTable = true;
      });

  }
  getProyectsGerente() {
      this.ClienteServices.getProyectsTablero(0).subscribe(res => {
        this.dataSource = res;
        this.showTable = true;
      });
  }


  showModulos(num) {
    switch (num) {
      case 1:
        return 'Clima laboral';
      case 2:
        return 'Evaluación de desempeño';
      case 3:
        return 'Evaluación Organizacional 360';
      case 4:
        return 'Administración por objetivos';
      case 5:
        return 'Plan y vida carrera';
    }
  }

}
