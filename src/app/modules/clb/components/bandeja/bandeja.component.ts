import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {MatButtonModule} from '@angular/material/button';
import {ProyectoService} from '../../services/proyecto.service';
import {Proyecto} from '../../models/proyecto';
import {CatalogsService} from '../../services/catalogs.service';
import Swal from "sweetalert2";
import {LoginService} from '../../services/login.service';


@Component({
  selector: 'app-bandeja',
  templateUrl: './bandeja.component.html',
  styleUrls: ['./bandeja.component.scss']
})
export class BandejaComponent implements OnInit {
  proyects: Proyecto[];
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoService: ProyectoService, private LoginService: LoginService) {
  }

  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'FechaFinal', 'frecuenciaEval', 'detail4', 'detail3'];
  dataSource = [];
  user = {};
  showTable = false;


  ngOnInit() {


    this.getUser();
  }

  getProyects() {

    this.ProyectoService.getProyects().subscribe((res) => {
      this.proyects = res;
      this.dataSource = this.proyects;
      this.showTable = true;
    });

  }

  goDetailProyect(element) {
    if (element) {
      console.log(element);
      sessionStorage.setItem('detail', JSON.stringify(element));
      this.responseChildren.emit({value: 10});

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
    this.showTable = false;
    this.ProyectoService.getProyectsbyRol(id).subscribe((res) => {
      if (res && res.length !== 0) {
        this.proyects = res;
        this.dataSource = this.proyects;
        this.showTable = true;
      }

    });
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
