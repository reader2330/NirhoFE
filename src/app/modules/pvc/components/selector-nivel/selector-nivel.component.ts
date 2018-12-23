import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import {MatSnackBar} from '@angular/material';
import {Esfera} from '../../models/esfera';
import Swal from "sweetalert2";
import {Nivel} from '../../models/nivel';

@Component({
  selector: 'app-selector-nivel',
  templateUrl: './selector-nivel.component.html',
  styleUrls: ['./selector-nivel.component.scss']
})
export class SelectorNivelComponent implements OnInit {

  proyect = {};
  proyects = [];
  area = {};
  esfera = {};
  areas = [];
  esferas = [];
  nivel = '';
  niveles = [];
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
  getEsferas() {
    this.ProyectServices.getEsferas(this.area['id']).subscribe(res => {
      this.esferas = res;
    });
  }

  getNiveles() {
    this.ProyectServices.getNiveles(this.esfera['id']).subscribe( res => {
      console.log(res);
      this.niveles = res;
    });
  }

  addNivel() {
    let add = true;
    for (let nivel of this.niveles) {
      if (nivel.nombre.trim().toUpperCase() === this.nivel.trim().toUpperCase()) {
        add = false;
        break;
      }
    }
    if (add) {
      let nivel = new Nivel();
      nivel.id = null;
      nivel.nombre = this.nivel;
      nivel.status = true;
      this.niveles.push(nivel);

    } else {
      this.snackBar.open('No puedes agregar dos areas con el mismo nombre', 'Claro!', {
        duration: 1000
      });
    }
    this.nivel = '';
  }

  guardarNivel() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar los niveles',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        console.log(this.esfera);
        this.ProyectServices.guardarNiveles(this.esfera['id'], this.niveles).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.niveles = [];
          });
        });
      }
    });

  }

}
