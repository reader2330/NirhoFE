import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-enterprise-irh',
  templateUrl: './enterprise-irh.component.html',
  styleUrls: ['./enterprise-irh.component.scss']
})
export class EnterpriseIrhComponent implements OnInit {
  mobile = false;
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

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  checkMaxColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMaxTwoColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  constructor() { }

  ngOnInit() {
  }

}
