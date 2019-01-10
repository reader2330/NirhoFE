import {Component, Inject, OnInit} from '@angular/core';
import {ReclutamientoService} from '../../rys/services/reclutamiento.service';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {Entrevista} from '../../rys/models/entrevista';
import Swal from 'sweetalert2';
import {LoginService} from '../../clb/services/login.service';

@Component({
  selector: 'app-selector-modal',
  templateUrl: './selector-modal.component.html',
  styleUrls: ['./selector-modal.component.scss']
})
export class SelectorModalComponent implements OnInit {
  candidatos = [];
  solicitantes = [];
  vacantes = [];
  selectCandidato = {};
  selectSolicitante = {};
  selectVacante = {};
  entrevista = new Entrevista();
  user = {};
  constructor(@Inject(MAT_DIALOG_DATA) public data, private Reclutamiento: ReclutamientoService, private modal: MatDialog, private Login: LoginService) {
  }

  ngOnInit() {
    this.getCandidatos();
    this.getSolicitantes();
    this.getVacantes();
    this.getUser();
  }

  getUser() {
    this.Login.getUser().subscribe(res => {
      this.user = res;
    });
  }
  getCandidatos() {
    this.Reclutamiento.getCandidatos().subscribe(res => {
      this.candidatos = res;
    });
  }

  getSolicitantes() {
    this.Reclutamiento.getSolicitantes().subscribe(res => {
      console.log(res);
      this.solicitantes = res;
    });
  }

  getVacantes() {
    this.Reclutamiento.getVacantes().subscribe(res => {
      console.log(res);
      this.vacantes = res;
    });
  }

  asignarVacante() {
    this.Reclutamiento.setVacante(this.selectCandidato['id'], this.selectVacante['id']).subscribe(res => {
      console.log(res);
    });
    this.modal.closeAll();
  }

  saveEntrevista(num?) {
    if (num) {
      this.modal.closeAll();
      return;
    }
    Swal({
      title: '',
      text: 'Seguro que quieres guardar los datos',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.entrevista.idConsultor = this.user['id'];
        this.Reclutamiento.saveEntrevista(this.entrevista).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.modal.closeAll();
          });
        });
      }
    });
  }
}
