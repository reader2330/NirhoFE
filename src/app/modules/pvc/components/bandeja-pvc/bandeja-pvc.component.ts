import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {MatButtonModule} from '@angular/material/button';



import Swal from "sweetalert2";
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';
import {LoginService} from '../../../clb/services/login.service';





@Component({
  selector: 'app-bandeja-pvc',
  templateUrl: './bandeja-pvc.component.html',
  styleUrls: ['./bandeja-pvc.component.scss']
})
export class BandejaPVCComponent implements OnInit {
  proyects: Proyecto[];
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoService: ProyectoPVCService, private LoginService: LoginService) {
  }

  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'frecuenciaEval', 'detail4', 'detail3'];
  dataSource = [];
  user = {};


  ngOnInit() {
    this.getUser();
  }

  getProyects() {

    this.ProyectoService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
      this.dataSource = this.proyects;
    });

  }

  goDetailProyect(element) {
    if (element) {
      console.log(element);
      sessionStorage.setItem('detail', JSON.stringify(element));
      this.responseChildren.emit({value: 11});

    }
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
    this.ProyectoService.getProyectsbyRol(id).subscribe((res) => {
      console.log(res);
      this.proyects = res;
      this.dataSource = this.proyects;
    });
  }
  cerrarProyecto(element) {

    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectoService.closeProyect(element['idProyecto']).subscribe(res => {
          Swal(
            'Listo.',
            'Se ha cerrado correctamente el proyecto',
            'success'
          );
        });
      }
    });
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



}
