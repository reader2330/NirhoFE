import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import {MatSnackBar} from '@angular/material';
import Swal from "sweetalert2";
import {Esfera} from '../../models/esfera';

@Component({
  selector: 'app-selecto-esferas',
  templateUrl: './selecto-esferas.component.html',
  styleUrls: ['./selecto-esferas.component.scss']
})
export class SelectoEsferasComponent implements OnInit {
  proyect = {};
  proyects = [];
  area = {};
  areas = [];
  esfera = '';
  esferas = [];
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
    this.areas = [];
    this.ProyectServices.getAreasByProyect(this.proyect['idProyecto']).subscribe(res => {
      this.areas = res;
    });
  }

  getEsferas() {
    this.esferas = [];
    console.log(this.area);
    this.ProyectServices.getEsferas(this.area['id']).subscribe(res => {
      this.esferas = res;
    });
  }

  addEsfera() {
    let add = true;
    for (let esfera of this.esferas) {
      if (esfera.nombre.trim().toUpperCase() === this.esfera.trim().toUpperCase()) {
        add = false;
        break;
      }
    }
    if (add) {
      let esfera = new Esfera();
      esfera.id = null;
      esfera.nombre = this.esfera;
      esfera.status = true;
      this.esferas.push(esfera);

    } else {
      this.snackBar.open('No puedes agregar dos areas con el mismo nombre', 'Claro!', {
        duration: 1000
      });
    }
    this.esfera = '';
  }

  guardarEsfera() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las esferas',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectServices.guardarEsferas(this.area['id'], this.esferas).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.esferas = [];
          });
        });
      }
    });

  }

}
