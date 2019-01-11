import {Component, Inject, OnInit} from '@angular/core';
import {ReclutamientoService} from '../../rys/services/reclutamiento.service';
import {Contrato} from '../../rys/models/contrato';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import Swal from "sweetalert2";

@Component({
  selector: 'app-modal-contrato',
  templateUrl: './modal-contrato.component.html',
  styleUrls: ['./modal-contrato.component.scss']
})
export class ModalContratoComponent implements OnInit {
  candidatos = [];
  contrato = new Contrato();
  type = false;
  constructor(private Reclutamiento: ReclutamientoService, @Inject(MAT_DIALOG_DATA) public data, private modal: MatDialog) { }
  ngOnInit() {
    this.getCandidatos();
    if (this.data.idVacante) {
      this.contrato.idVacante = this.data.idVacante;
      this.Reclutamiento.getContrato(this.data.idVacante).subscribe(res => {
        console.log(res);
        if  (res && res[0]) {
          this.contrato.id = res[0].id;
          this.contrato.jornada = res[0].jornada;
          this.contrato.prestaciones = res[0].prestaciones;
          this.contrato.sueldo = res[0].sueldo;
          this.contrato.tipoContrato = res[0].tipoContrato;
          this.contrato.aceptado = res[0].aceptado;
          this.contrato.idVacante = res[0].idVacante;
          this.contrato.idCandidato = res[0].idCandidato;
        }
      });
    }
    if (this.data.type) {
      this.type = this.data.type;
    }
  }
   getCandidatos() {
    this.Reclutamiento.getCandidatos().subscribe(res => {
      this.candidatos = res;
    });
  }
  saveContrato() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar los datos',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.Reclutamiento.saveContrato(this.contrato).subscribe(res => {
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
