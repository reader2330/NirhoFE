import { Component, OnInit } from '@angular/core';
import {GeneralService} from '../../services/general.service';
import {ReclutamientoService} from '../../../rys/services/reclutamiento.service';

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
  candidato = {};
  vacante = {}
  candidatos = [];
  vacantes = [];
  empresas = [];
  empresa = {};
  proyectsNull = false;
  typeReporte = {};
  tipos = [
    {
      key: 1,
      name: 'CV Nirho'
    },
    {
      key: 2,
      name: 'Reporte inicial'
    },
    {
      key: 3,
      name: 'Reporte final'
    }
  ]
  constructor(private GeneralServices: GeneralService, private Reclutamiento: ReclutamientoService) { }

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
      if (this.moduleSelect === 'RYS') {
        this.getParticipantes();
      } else {
        this.getProyects();
      }

    }
  }

  getProyects() {
    if (this.moduleSelect === 'RYS') {
      this.proyectsNull = true;
      return;
    }
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
        if (this.moduleSelect === 'RYS') {
          this.GeneralServices.getCandidatos().subscribe(res => {
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

  }

  generarReporte() {
    if (!this.participante['idParticipante'] && !this.participante['participantePK']) {
      if (this.moduleSelect === 'RYS'){
        this.GeneralServices.generarReporteCandidato(this.participante['id']);
        return;
      }
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
    if (this.moduleSelect === 'RYS') {
      return false;
    }
    if (this.moduleSelect === 'IRH') {
      return !this.empresa['id'];
    } else {
      return !this.proyect['idProyecto'];
    }
  }
  distribuitorGenerarReporte() {
    switch (this.typeReporte['key']) {
      case 1:
        this.generarCVNirho();
        break;
      case 2:
        this.generaReporteInicial();
      case 3:
        this.generarReporteFinal();
    }
  }
  distribuitorTipoReporte() {
    this.candidatos = [];
    this.vacantes = [];
    if (this.typeReporte['key'] === 1) {
      this.getCandidatos();
    } else {
      this.getVacantes();
    }
  }
  getVacantes() {
    this.Reclutamiento.getVacantes().subscribe(res => {
      console.log(res);
      this.vacantes = res;
    });
  }

  getCandidatos() {
    this.Reclutamiento.getCandidatos().subscribe(res => {
      this.candidatos = res;
    });
  }
  generarCVNirho() {
    this.GeneralServices.generarReporteCandidato(this.candidato['id']);
  }
  generaReporteInicial() {
    this.GeneralServices.generarReporteInicial(this.vacante['id']);
  }
  generarReporteFinal() {
    this.GeneralServices.generarReporteFinal(this.vacante['id']);
  }

}
