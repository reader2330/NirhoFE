import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import {MatSnackBar} from '@angular/material';
import {Especialidad} from '../../models/especialidad';
import Swal from "sweetalert2";
import {Conocimiento} from '../../models/conocimiento';

@Component({
  selector: 'app-selector-conocimiento-tecnicos',
  templateUrl: './selector-conocimiento-tecnicos.component.html',
  styleUrls: ['./selector-conocimiento-tecnicos.component.scss']
})
export class SelectorConocimientoTecnicosComponent implements OnInit {
  @Input('type') type: number;
  proyect = {};
  proyects = [];
  area = {};
  esfera = {};
  areas = [];
  esferas = [];
  nivel = {};
  niveles = [];
  especialidades = [];
  especialidad = {};
  conocimiento = '';
  conocimientos = [] ;
  @Output() response = new EventEmitter();

  constructor(public ProyectServices: ProyectoPVCService, public snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getProyects();
    console.log(this.type);
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
      this.niveles = res;
    });
  }
  getEspecialiades() {
    this.ProyectServices.getEspecialidades(this.nivel['id']).subscribe(res => {
      this.especialidades = res;
    });
  }
  getConocimientos() {
    this.ProyectServices.getConocimientos(this.especialidad['id']).subscribe(res => {
      this.conocimientos = res;
    });
  }
  addConocimiento() {
    let add = true;
    for (let conocimiento of this.conocimientos) {
      if (conocimiento.nombre.trim().toUpperCase() === this.conocimiento.trim().toUpperCase()) {
        add = false;
        break;
      }
    }
    if (add) {
      let conocimiento = new Conocimiento();
      conocimiento.id = null;
      conocimiento.nombre = this.conocimiento;
      conocimiento.tipo = this.type;
      this.conocimientos.push(conocimiento);

    } else {
      this.snackBar.open('No puedes agregar dos areas con el mismo nombre', 'Claro!', {
        duration: 1000
      });
    }
    this.conocimiento  = '';
  }

  guardarConocimientos() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las especialidades',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectServices.saveConocimientos(this.especialidad['id'], this.conocimientos, this.type).subscribe(res => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.conocimientos = [];
          });
        });
      }
    });

  }

}
