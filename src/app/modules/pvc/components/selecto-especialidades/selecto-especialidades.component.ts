import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import {MatSnackBar} from '@angular/material';
import {Nivel} from '../../models/nivel';
import Swal from "sweetalert2";
import {Especialidad} from '../../models/especialidad';

@Component({
  selector: 'app-selector-especialidades',
  templateUrl: './selecto-especialidades.component.html',
  styleUrls: ['./selecto-especialidades.component.scss']
})
export class SelectoEspecialidadesComponent implements OnInit {

  proyect = {};
  proyects = [];
  area = {};
  esfera = {};
  areas = [];
  esferas = [];
  nivel = {};
  niveles = [];
  especialidades = [];
  especialidad = '';
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
  getEspecialiades() {
    this.ProyectServices.getEspecialidades(this.nivel['id']).subscribe(res => {
      this.especialidades = res;
    });
  }

  addEspecialidad() {
    let add = true;
    for (let espec of this.especialidades) {
      if (espec.nombre.trim().toUpperCase() === this.especialidad.trim().toUpperCase()) {
        add = false;
        break;
      }
    }
    if (add) {
      let especialidad = new Especialidad();
      especialidad.id = null;
      especialidad.nombre = this.especialidad;
      especialidad.status = true;
      this.especialidades.push(especialidad);

    } else {
      this.snackBar.open('No puedes agregar dos areas con el mismo nombre', 'Claro!', {
        duration: 1000
      });
    }
    this.especialidad = '';
  }

  guardarEspecialidades() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las especialidades',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        console.log(this.nivel);
        this.ProyectServices.saveEspecialidades(this.nivel['id'], this.especialidades).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.especialidades = [];
          });
        });
      }
    });

  }

}
