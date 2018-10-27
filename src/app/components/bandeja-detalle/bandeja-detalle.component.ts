import { Component, OnInit } from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';


@Component({
  selector: 'app-bandeja-detalle',
  templateUrl: './bandeja-detalle.component.html',
  styleUrls: ['./bandeja-detalle.component.scss']
})
export class BandejaDetalleComponent implements OnInit {

  proyect = {
    idProyecto: 0,
    nombre: '',
    numEmpleados: 0,
    sedes: '',
    numParticipantes: 0,
    frecuenciaEval: 0,
    idEmpresa: {},
    idContacto: {},
  };

  contact = {
    id: 0,
    celular: 1234,
    email: '',
    nombre: '',
    puesto: '',
    telefono: 1234,
    tipoContacto: 1,
    empresa: {}
  };

  company = {
    id: null,
    direccion: '',
    empresa: '',
    giro: 0,
    pais: 0,
    rfc : '',
  };

  panelOpenState = false;

  constructor() { }

  ngOnInit() {
  }

}
