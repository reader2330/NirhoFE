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
    if (this.moduleSelect === 'EVA360') {
      this.GeneralServices.getProyectsByModule('EVO360').subscribe(res => {
        this.proyects = res;
      });
    } else {
      this.GeneralServices.getProyectsByModule(this.moduleSelect).subscribe(res => {
        this.proyects = res;
      });
    }
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
      if (this.moduleSelect === 'EVD' || this.moduleSelect === 'EVA360') {
        if (this.moduleSelect === 'EVA360') {
          this.GeneralServices.getParticipantebyProyect(this.proyect['idProyecto']).subscribe(res => {
            console.log(res);
            this.participantes = res;
          });
        }
        this.GeneralServices.getParticipantebyProyect(this.proyect['idProyecto']).subscribe(res => {
          console.log(res);
          this.participantes = res;
        });
      } else {
        this.GeneralServices.getParticipantebyProyect(this.proyect['idProyecto'], this.moduleSelect).subscribe(res => {
          console.log(res);
          this.participantes = res;
        });
      }

    }

  }

  generarReporte() {
    console.log(this.participante);
    if (!this.participante['idParticipante'] && !this.participante['participantePK']) {
      if (this.moduleSelect === 'IRH') {
        this.GeneralServices.generarReporteCompany(this.empresa['id']);
      } else {
        if (this.moduleSelect === 'EVA360') {
          this.GeneralServices.generarReporteProyect('EVO360', this.proyect['idProyecto']);
        }
        this.GeneralServices.generarReporteProyect(this.moduleSelect, this.proyect['idProyecto']);
      }
    } else {
      if (this.moduleSelect === 'EVD' || this.moduleSelect === 'EVA360') {
        if (this.moduleSelect === 'EVD') {
          this.GeneralServices.generarReporteParticipante(this.moduleSelect, this.participante['participantePK']['idParticipante'], this.proyect['idProyecto']);
        }
        if (this.moduleSelect === 'EVA360') {
          this.GeneralServices.generarReporteParticipante('EVO360', this.participante['participantePK']['idParticipante'], this.proyect['idProyecto']);
        }
      } else {
        if (this.moduleSelect === 'PVC') {
          this.GeneralServices.generarReporteParticipantePVC(this.participante['idParticipante']);
        } else {
          this.GeneralServices.generarReporteParticipante(this.moduleSelect, this.participante['idParticipante']);
        }

      }
    }
  }
  BloqueoBoton() {
    if (this.moduleSelect === 'IRH') {
      return !this.empresa['id'];
    } else {
      return !this.proyect['idProyecto'];
    }
  }

}
