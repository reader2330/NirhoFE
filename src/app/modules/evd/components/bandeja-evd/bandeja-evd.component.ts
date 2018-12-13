import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import {LoginService} from '../../../clb/services/login.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-bandeja-evd',
  templateUrl: './bandeja-evd.component.html',
  styleUrls: ['./bandeja-evd.component.scss']
})
export class BandejaEvdComponent implements OnInit {

  proyects: Proyecto[];
  user =  {};
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoEvdServices: ProyectoEvdService, private LoginService: LoginService) {
  }

  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'frecuenciaEval', 'detail4', 'detail3', ];
  dataSource = [];

  ngOnInit() {
    this.getUser();
  }

  getProyects() {

    this.ProyectoEvdServices.getProyects().subscribe((res) => {

      this.proyects = res;
      this.dataSource = this.proyects;
    });

  }

  goDetailProyect(element) {
    if (element) {
      sessionStorage.setItem('detail', JSON.stringify(element));
      this.responseChildren.emit({value: 10});

    }
  }

  getUser() {
    this.LoginService.getUser().subscribe(res => {

      this.user = res;
      if (this.user['id']) {
        this.getUserProyects();
      }
    });
  }
  getUserProyects() {
      if (this.user['rol'] === 2 || this.user['rol'] === 1) {
        this.getProyects();
      } else {
        this.getProyectsbyRol(this.user['id']);
      }
    }
  getProyectsbyRol(id) {
    this.ProyectoEvdServices.getProyectsbyRol(id).subscribe((res) => {

      this.proyects = res;
      this.dataSource = this.proyects;
    });
  }
  getFrecuencia(num) {

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
    if (num === 5 ) {
      return 'Unica vez';
    }

  }
  checkConcierge() {
    if (this.user['rol'] === 2) {
      return false;
    }
    return true;
  }
  checkDetalle() {
    if (this.user['rol'] === 2 || this.user['rol'] === 3) {
      return false;
    } else {
      return true;
    }
  }
  cerrarProyecto(element) {

    Swal({
      title: '',
      text: 'Seguro que quieres cerrar el proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.ProyectoEvdServices.closeProyect(element['idProyecto']).subscribe(res => {
          Swal(
            'Listo.',
            'Se ha cerrado correctamente el proyecto',
            'success'
          );
        });
      }
    });
  }
}
