import { Component, OnInit } from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {SelectorModalComponent} from '../../modal/selector-modal/selector-modal.component';
import {computeStyle} from '@angular/animations/browser/src/util';
import {ModalComentarioComponent} from '../../modal/modal-comentario/modal-comentario.component';
import {LoginService} from '../../clb/services/login.service';


@Component({
  selector: 'app-candidato-table',
  templateUrl: './candidato-table.component.html',
  styleUrls: ['./candidato-table.component.scss'],
})
export class CandidatoTableComponent implements OnInit {
  candidatos = new MatTableDataSource();
  entrevistas = new MatTableDataSource();
  showTable2 = false;
  showTable = false;
  displayVacantes = ['nombre', 'rfc', 'perfil', 'pretencion'];
  displayEntrevistas = ['direccion', 'titulo', 'fechaEntrevista', 'Comentario'];
  hasCandidato = false;
  hasGerente = false;
  expandedElement: null;
  user = {};
  constructor(private ReclutamientoServices: ReclutamientoService, private modal: MatDialog, private Login: LoginService) { }

  ngOnInit() {
    if (sessionStorage.getItem('Candidato')) {
      this.hasCandidato = true;
    }
    this.getCandidatos();
    this.getEntrevistas();
    this.getUser();
  }
  getCandidatos() {
    this.ReclutamientoServices.getCandidatos().subscribe(res => {
      console.log(res);
      this.candidatos.data = res;
      this.showTable = true;
    });
  }
  applyFilter(filterValue: string, num) {
    switch (num) {
      case 1:
        this.candidatos.filter = filterValue.trim().toLowerCase();
        break;
      case 2:
        this.entrevistas.filter = filterValue.trim().toLowerCase();
        break;
    }
  }

  openModal(num) {
    this.modal.open(SelectorModalComponent, {
      data: {
        type: num
      }
    });
  }
  getEntrevistas() {
    this.ReclutamientoServices.getEntrevista().subscribe(res => {
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
  getUser() {
    this.Login.getUser().subscribe(res => {
      this.user = res;
    });
  }


}
