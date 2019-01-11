import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {LoginService} from '../../clb/services/login.service';
import {ModalComentarioComponent} from '../../modal/modal-comentario/modal-comentario.component';


@Component({
  selector: 'app-vacante-table',
  templateUrl: './vacante-table.component.html',
  styleUrls: ['./vacante-table.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class VacanteTableComponent implements OnInit {
  vacantes = new MatTableDataSource();
  showTable = false;
  displayVacantes = ['Vacante', 'numeroVacantes', ];
  displayEntrevistas = ['direccion', 'titulo', 'fechaEntrevista', 'Comentario'];
  hasCandidato = false;
  hasGerente = false;
  expandedElement: null;
  user = {};
  solicitante = {};
  entrevistas = [];
  showTable2 = false;
  constructor(private ReclutamientoServices: ReclutamientoService, private Login: LoginService, public modal: MatDialog) { }

  ngOnInit() {
    this.getUser();

  }
  getVacantesBySolicitante() {
    this.ReclutamientoServices.getVacantesBySolicitante(this.user['username']).subscribe(res => {
      this.vacantes = res;
    });
  }
  getVacantes() {
    this.ReclutamientoServices.getVacantes().subscribe(res => {
      this.vacantes.data = res;
      this.showTable = true;
    });
  }
  removeVacante(element) {
    this.vacantes.data.map((item, i) => {
      if (item['id'] === element.id) {
        this.vacantes.data.slice(i, 1);
      }
    });
    this.ReclutamientoServices.removeVacante(element.id).subscribe(res => {
    });
  }
  setVacante(element) {
    let candidato = JSON.parse(sessionStorage.getItem('Candidato'));
    this.ReclutamientoServices.setVacante(candidato['id'], element.id).subscribe(res => {
    });
  }
  applyFilter(filterValue: string) {
    this.vacantes.filter = filterValue.trim().toLowerCase();
  }
  getUser() {
    this.Login.getUser().subscribe(res => {
      this.user = res;
      if (this.user['rol'] < 4) {
        this.getVacantes();
        this.getEntrevistas();
      } else {
        this.getEntrevistasByCandidato();
      }
    });
  }
  getEntrevistas() {
    this.ReclutamientoServices.getEntrevista().subscribe(res => {
      this.entrevistas = res;
      this.showTable2 = true;
    });
  }

  getEntrevistasByCandidato() {
    this.ReclutamientoServices.getEntrevistaByType('Candidato', this.user['username']).subscribe(res => {
      console.log(res);
      this.entrevistas = res;
      this.showTable2 = true;
    });
  }

  modalComentario(element) {
    this.modal.open(ModalComentarioComponent, {
      data: {
        type: this.user['rol'],
        entrevista: element
      }
    });
  }



}
