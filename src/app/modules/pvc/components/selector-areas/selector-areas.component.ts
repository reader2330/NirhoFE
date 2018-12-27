import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import {Area} from '../../models/area';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-selector-areas',
  templateUrl: './selector-areas.component.html',
  styleUrls: ['./selector-areas.component.scss']
})
export class SelectorAreasComponent implements OnInit {
  proyect = {};
  proyects = [];
  area = '';
  areas = [];
  @Output() response = new EventEmitter();

  constructor(public ProyectServices: ProyectoPVCService, public snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getProyects();
  }

  getProyects() {
    this.ProyectServices.getProyects().subscribe(res => {
      this.proyects = res;
    });
  }
  getAreas() {
    this.ProyectServices.getAreasByProyect(this.proyect['idProyecto']).subscribe(res => {
      this.areas = res;
    });
  }

  addArea() {
    let add = true;
    for (let area of this.areas) {
      if (area.nombre.trim().toUpperCase() === this.area.trim().toUpperCase()) {
        add = false;
        break;
      }
    }
    if (add) {
      let area = new Area();
      area.id = null;
      area.idProyecto = this.proyect['idProyecto'];
      area.nombre = this.area;
      area.status = true;
      this.areas.push(area);

    } else {
      this.snackBar.open('No puedes agregar dos areas con el mismo nombre', 'Claro!', {
        duration: 1000
      });
    }

    this.area = '';
  }

  guardarAreas() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las areas',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectServices.guardarAreas(this.areas).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });

        });
      }
    });

  }
}
