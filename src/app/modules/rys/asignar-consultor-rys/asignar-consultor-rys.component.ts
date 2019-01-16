import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import Swal from "sweetalert2";
import {ReclutamientoService} from '../services/reclutamiento.service';


@Component({
  selector: 'app-asignar-consultor-rys',
  templateUrl: './asignar-consultor-rys.component.html',
  styleUrls: ['./asignar-consultor-rys.component.scss']
})
export class AsignarConsultorRysComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  proyects = [];
  consultores = [];
  proyect = {};
  consultor = {};

  constructor(private ProyectService: ReclutamientoService) {
  }

  ngOnInit() {
    this.getProyects();
    this.getConsultores();
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  getProyects() {
    this.ProyectService.getVacantes().subscribe((res) => {
      this.proyects = res;
    });
  }

  getConsultores() {
    this.ProyectService.getConsultores().subscribe((res) => {
      this.consultores = res;
    });
  }

  saveConsultor() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada de la vacante',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {

        this.ProyectService.saveConsultor(this.proyect['id'], this.consultor['id']).subscribe((res) => {
          console.log(res);
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        }, (err) => {
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
