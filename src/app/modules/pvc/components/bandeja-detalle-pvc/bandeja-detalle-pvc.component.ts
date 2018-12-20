import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Proyecto} from '../../../clb/models/proyecto';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import Swal from "sweetalert2";
import {ProyectoPVCService} from '../../services/proyectoPVC.service';


@Component({
  selector: 'app-bandeja-detalle-pvc',
  templateUrl: './bandeja-detalle-pvc.component.html',
  styleUrls: ['./bandeja-detalle-pvc.component.scss']
})
export class BandejaDetallePvcComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();
  paises = [];
  giros = [];
  puestos = [];
  tiposContacto = [];
  data: Proyecto;
  panelOpenState = true;

  constructor(private CatalogService: CatalogsService, private ProyectServices: ProyectoPVCService) { }

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
              this.responseChildren.emit({value: 1});
            });

          },
          (err) => {
            console.log(err);
            Swal(
              'Algo salio mal.',
              'No se pudo guarda la información',
              'error'
            ).then(() => {
              this.responseChildren.emit({value: 1});
            });

          });
      }
    });

  }

}
