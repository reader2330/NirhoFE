import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {ReclutamientoService} from '../../rys/services/reclutamiento.service';

@Component({
  selector: 'app-modal-comentario',
  templateUrl: './modal-comentario.component.html',
  styleUrls: ['./modal-comentario.component.scss']
})
export class ModalComentarioComponent implements OnInit {
  comentario = '';
  constructor(@Inject(MAT_DIALOG_DATA) public data, private Reclutamiento: ReclutamientoService, private modal: MatDialog) { }

  ngOnInit() {
    switch (this.data.type) {
      case 3:
        this.comentario = this.data.entrevista.observacionesConsultor;
        break;
      case 5:
        this.comentario = this.data.entrevista.observacionesCliente;
        break;
      case 6:
        this.comentario = this.data.entrevista.observacionesSolicitante;
        break;
    }
  }
  distribuidorComentario() {
    switch (this.data.type) {
      case 3:
        this.data.entrevista.observacionesConsultor = this.comentario;
        this.Reclutamiento.saveComentario(this.data.entrevista.id, 'consultor', this.comentario).subscribe(res => {
          this.modal.closeAll();
        });
        break;
      case 5:
        this.data.entrevista.observacionesCliente = this.comentario;
        this.Reclutamiento.saveComentario(this.data.entrevista.id, 'candidato', this.comentario).subscribe(res => {
          this.modal.closeAll();
        });
        break;
      case 6:
        this.data.entrevista.observacionesSolicitante = this.comentario;
        this.Reclutamiento.saveComentario(this.data.entrevista.id, 'solicitante', this.comentario).subscribe(res => {
          this.modal.closeAll();
        });
    }

  }

}
