import { Component, OnInit } from '@angular/core';
import {GeneralService} from '../../services/general.service';

@Component({
  selector: 'app-generador-reportes',
  templateUrl: './generador-reportes.component.html',
  styleUrls: ['./generador-reportes.component.scss']
})
export class GeneradorReportesComponent implements OnInit {
  moduleSelect = '';
  proyect = {};
  proyects = [];
  participante = {};
  participantes = [];
  empresas = [];
  empresa = {};
  constructor(private GeneralServices: GeneralService) { }

  ngOnInit() {
    if (sessionStorage.getItem('moduleActive')) {
      this.moduleSelect = sessionStorage.getItem('moduleActive');
    }
    this.distribuitorModules();
  }

  distribuitorModules() {
    if (this.moduleSelect === 'IRH') {
      this.getEmpresas();
    } else {
      this.getProyects();
    }
  }

  getProyects() {
    this.GeneralServices.getProyectsByModule(this.moduleSelect).subscribe(res => {
        this.proyects = res;

    });
  }
  getEmpresas() {
    this.GeneralServices.getEmpresas().subscribe(res => {
      console.log(res);
      this.empresas = res;
    });
  }

  getParticipantes() {

    if (this.moduleSelect === 'CLB' || this.moduleSelect === 'IRH') {
      return;
    } else {
      this.GeneralServices.getParticipantebyProyect(this.proyect['idProyecto']).subscribe(res => {
        console.log(res);
        this.participantes = res;
      });
    }

  }

  generarReporte() {

    if (!this.participante['id']) {
      if (this.moduleSelect === 'IRH') {
        this.GeneralServices.generarReporteCompany(this.empresa['id']);
      } else {
        this.GeneralServices.generarReporteProyect(this.moduleSelect, this.proyect['idProyecto']);
      }
    } else {
      this.GeneralServices.generarReporteParticipante(this.moduleSelect, this.proyect['idProyecto']).subscribe(res => {
        console.log(res);
      });
    }

  }

  BloqueoBoton() {
    if (this.moduleSelect === 'IRH') {
      return !this.empresa['id'];
    } else {
      return !this.proyect['id'];
    }
  }

}
