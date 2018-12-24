import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import Swal from "sweetalert2";
import {ProyectoPVCService} from '../../services/proyectoPVC.service';


@Component({
  selector: 'app-asignar-consultor-pvc',
  templateUrl: './asignar-consultor-pvc.component.html',
  styleUrls: ['./asignar-consultor-pvc.component.scss']
})
export class AsignarConsultorPvcComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  proyects = [];
  consultores = [];
  proyect = {};
  consultor = {};

  constructor(private ProyectService: ProyectoPVCService) {
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
    this.ProyectService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }

  getConsultores() {
    this.ProyectService.getConsultores().subscribe((res) => {
      console.log(res);
      this.consultores = res;
    });
  }

  saveConsultor() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        let data = {
          idProyecto: this.proyect['idProyecto'],
          idUsuario: this.consultor['id']
        };
        this.ProyectService.saveConsultor(data).subscribe((res) => {
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