import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../../rys/services/reclutamiento.service';

@Component({
  selector: 'app-modal-contrato',
  templateUrl: './modal-contrato.component.html',
  styleUrls: ['./modal-contrato.component.scss']
})
export class ModalContratoComponent implements OnInit {
  candidatos = [];
  constructor(private Reclutamiento: ReclutamientoService) { }

  ngOnInit() {
    this.getCandidatos();
  }
   getCandidatos() {
    this.Reclutamiento.getCandidatos().subscribe(res => {
      this.candidatos = res;
    });
  }

}
