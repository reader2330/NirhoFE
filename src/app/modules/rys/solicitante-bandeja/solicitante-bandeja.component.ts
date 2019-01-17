import { Component, OnInit } from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {LoginService} from '../../clb/services/login.service';
import {ModalComentarioComponent} from '../../modal/modal-comentario/modal-comentario.component';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {ModalContratoComponent} from '../../modal/modal-contrato/modal-contrato.component';
import {el} from '@angular/platform-browser/testing/src/browser_util';
import {ModalCandidatosComponent} from '../../modal/modal-candidatos/modal-candidatos.component';
import Swal from "sweetalert2";

@Component({
  selector: 'app-solicitante-bandeja',
  templateUrl: './solicitante-bandeja.component.html',
  styleUrls: ['./solicitante-bandeja.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SolicitanteBandejaComponent implements OnInit {
  vacantes = new MatTableDataSource();
  showTable = false;
  displayVacantes = ['Vacante', 'numeroVacantes', 'detail1', 'detail2' ];
  colums = ['Vacante', 'numeroVacantes' ];
  displayEntrevistas = ['Candidato', 'direccion', 'titulo', 'fechaEntrevista', 'Comentario'];
  hasCandidato = false;
  hasGerente = false;
  expandedElement: null;
  user = {};
  solicitante = {};
  entrevistas = [];
  showTable2 = false;
  candidatos = [];

  first = true;
  constructor(private ReclutamientoServices: ReclutamientoService, private Login: LoginService, public modal: MatDialog) { }

  ngOnInit() {
    this.getUser();

  }
  getVacantesBySolicitante() {
    this.ReclutamientoServices.getVacantesBySolicitante(this.user['username']).subscribe(res => {
      this.vacantes = res;
      this.showTable = true;
    });
  }
  applyFilter(filterValue: string) {
    this.vacantes.filter = filterValue.trim().toLowerCase();
  }
  getUser() {
    this.Login.getUser().subscribe(res => {
      this.user = res;
      this.getEntrevistasBySolicitante();
      this.getVacantesBySolicitante();
    });
  }
  getEntrevistasBySolicitante() {
    this.ReclutamientoServices.getEntrevistaByType('Solicitante', this.user['username']).subscribe(res => {
      this.entrevistas = res;
      for (let ent of this.entrevistas) {
        this.ReclutamientoServices.getCandidatoByID(ent.idCandidato).subscribe(res2 => {
          this.candidatos.push(res2);
        });
      }
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

  OpenModalContrato(element) {
    this.modal.open(ModalContratoComponent, {
      data: {
        idVacante: element.id,
        type: true
      }
    });
  }

  getCandidato(id) {
    let name = '';
    for (let cand of this.candidatos) {
      if (id === cand['id']) {
        name = cand['nombre'];
      }
    }
    return name;
  }

  OpenModalCandidatos(element) {
    if (!element.candidatos.length) {
      return Swal(
        'Lo siento!',
        'No tiene candidatos asignados',
        'warning'
      );
    }
    this.modal.open(ModalCandidatosComponent, {
      data: {
        candidatos: element.candidatos
      }
    });
  }

}
