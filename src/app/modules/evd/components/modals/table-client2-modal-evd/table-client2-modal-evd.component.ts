import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-table-client2-modal-evd',
  templateUrl: './table-client2-modal-evd.component.html',
  styleUrls: ['./table-client2-modal-evd.component.scss']
})
export class TableClient2ModalEvdComponent implements OnInit {

  enterprise = {
    id: null,
    name: '',
    empresa: '',
    giro: 0,
    pais: 0,
    rfc : '',
  };

  enterpriseForm = new FormGroup(
    {
      id: new FormControl(null),
      anioInicioOperaciones: new FormControl(0),
      facturacionAnual: new FormControl(0),
      productoServicioEstrella: new FormControl(null),
      principalesProductosServicios: new FormControl(null),
      noEmpleadosAdministrativo: new FormControl(0),
      noEmpleadosOperativo: new FormControl(0),
      tipoContratacionEmpleados: new FormControl(null),
      direccion: new FormControl('', Validators.required),
      giro: new FormControl(0, Validators.required),
      pais: new FormControl(0, Validators.required),
      rfc: new FormControl('', Validators.required),
      empresa: new FormControl('', Validators.required)
    }
  );

  constructor() { }

  ngOnInit() {
  }

}
