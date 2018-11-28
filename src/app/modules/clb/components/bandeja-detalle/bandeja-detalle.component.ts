import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {CatalogsService} from '../../services/catalogs.service';

import {Proyecto} from '../../models/proyecto';
import {ProyectoService} from '../../services/proyecto.service';
import Swal from "sweetalert2";


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
  data:any = {
    idEmpresa: {},
    idContacto: {},
    nombre : '',
    numEmpleados: 0,
    numParticipantes: 0,
    sedes: ''

  };
  panelOpenState = true;
  muestra = {};


  constructor(private CatalogService: CatalogsService, private ProyectServices: ProyectoService) { }
  @Output() response = new EventEmitter();
  ngOnInit() {
    if (sessionStorage.getItem('detail')) {
      this.muestra = JSON.parse(sessionStorage.getItem('detail'));
      this.ProyectServices.getProyect(this.muestra['idProyecto']).subscribe(res => {
        console.log(res);
        this.data = res;
      })
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
      console.log(res);

    });
    this.CatalogService.getGiros().subscribe((res) => {
      this.giros = res;
    });

    this.CatalogService.getTypeContact().subscribe((res) => {
      this.tiposContacto = res;
    });
    this.CatalogService.getCountries().subscribe((res) => {
      this.paises = res;
    });

  }

  getNames(catalog, id ) {

    for (let cat of catalog ) {
      if (cat.id === id) {
        return cat.descripcionCatalogo;
      }
    }
    return '';
  }
  getPuestos() {
    this.CatalogService.getPuestos().subscribe((res) => {

      if (res) {
        this.puestos = res;
        console.log(res);
      }
    });

  }

  updateProyecto() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectServices.saveProyect(this.data).subscribe(res => {
            console.log(res);
            Swal(
              'Listo.',
              'La información se guardo correctamente',
              'success'
            ).then(() => {
              this.response.emit({value: 1});
            });

          },
          (err) => {
            console.log(err);
            Swal(
              'Algo salio mal.',
              'No se pudo guarda la información',
              'error'
            ).then(() => {
              this.response.emit({value: 1});
            });

          });
      }
    });

  }

}
