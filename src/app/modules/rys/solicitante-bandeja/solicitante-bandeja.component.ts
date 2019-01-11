import { Component, OnInit } from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {LoginService} from '../../clb/services/login.service';
import {ModalComentarioComponent} from '../../modal/modal-comentario/modal-comentario.component';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {ModalContratoComponent} from '../../modal/modal-contrato/modal-contrato.component';
import {el} from '@angular/platform-browser/testing/src/browser_util';

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
  displayVacantes = ['Vacante', 'numeroVacantes', 'detail1' ];
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

  OpenModalContrato(element) {
    console.log(element);
    this.modal.open(ModalContratoComponent, {
      data: {
        idVacante: element.id
      }
    });
  }

}
