import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {ReclutamientoService} from '../../rys/services/reclutamiento.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-modal-candidatos',
  templateUrl: './modal-candidatos.component.html',
  styleUrls: ['./modal-candidatos.component.scss']
})
export class ModalCandidatosComponent implements OnInit {
  candidatos = [];
  constructor(@Inject(MAT_DIALOG_DATA) public data, private Reclutamiento: ReclutamientoService, private modal: MatDialog) { }

  ngOnInit() {
    this.candidatos = this.data.candidatos;
  }
  eliminarCandidato(element) {
    Swal({
      title: '',
      text: 'Seguro que quieres eliminar al candidato',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.Reclutamiento.deleteCandidatoVacante(element.id).subscribe(res => {
          Swal(
            'Listo.',
            'El candidato se ha eliminado',
            'success'
          ).then(() => {
            this.modal.closeAll();
          });
        });
      }
    });
  }


}
