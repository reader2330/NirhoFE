import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import Swal from "sweetalert2";
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-asignar-consultor-apo',
  templateUrl: './asignar-consultor-apo.component.html',
  styleUrls: ['./asignar-consultor-apo.component.scss']
})
export class AsignarConsultorApoComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  proyects = [];
  consultores = [];
  proyect = {};
  consultor = {};

  constructor(private ProyectService: ProyectoApoService) {
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
          idUsuario: this.consultor['idUsuario']
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
