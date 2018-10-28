import { Component, OnInit } from '@angular/core';

import {CatalogsService} from '../../services/catalogs.service';

import {Proyecto} from '../../models/proyecto';


@Component({
  selector: 'app-bandeja-detalle',
  templateUrl: './bandeja-detalle.component.html',
  styleUrls: ['./bandeja-detalle.component.scss']
})
export class BandejaDetalleComponent implements OnInit {
  paises = [];
  giros = [];
  puestos = [];
  tiposContacto = [];
  data: Proyecto;
  panelOpenState = false;


  constructor(private CatalogService: CatalogsService) { }

  ngOnInit() {
    if (sessionStorage.getItem('detail')) {
      this.data = JSON.parse(sessionStorage.getItem('detail'));
      console.log(this.data);
      this.getCatalogos();
    }
  }
  getFrecuencia() {
    if (this.data) {
      const num = this.data['frecuenciaEval'];
      if (num === 1) {
        return 'Mensual';
      }
      if (num === 2) {
        return 'Bimestral';
      }
      if (num === 3) {
        return 'Semestral';
      }
      if (num === 4) {
        return 'Anual';
      }
    }
  }

  getCatalogos() {
    this.CatalogService.getPuestos().subscribe((res) => {
      this.puestos = res;
      this.data.idContacto.puesto = this.getNames(this.puestos, this.data.idContacto.puesto);
      console.log(this.data);
    });
    this.CatalogService.getGiros().subscribe((res) => {
      this.giros = res;
      this.data.idEmpresa.giro = this.getNames(this.giros, this.data.idEmpresa.giro);
    });

    this.CatalogService.getTypeContact().subscribe((res) => {
      this.tiposContacto = res;
      this.data.idContacto.tipoContacto = this.getNames(this.tiposContacto, this.data.idContacto.tipoContacto);
    });
    this.CatalogService.getCountries().subscribe((res) => {
      this.paises = res;
      this.data.idEmpresa.pais = this.getNames(this.paises, this.data.idEmpresa.pais);
    });

  }

  getNames(catalog, id, ) {

    for (let cat of catalog ) {
      if (cat.id === id) {
        return cat.descripcionCatalogo;
      }
    }
    return '';
  }

}
