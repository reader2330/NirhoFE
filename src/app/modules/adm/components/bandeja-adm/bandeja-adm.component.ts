import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';


@Component({
  selector: 'app-bandeja',
  templateUrl: './bandeja-adm.component.html',
  styleUrls: ['./bandeja-adm.component.scss']
})
export class BandejaAdmComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();

  constructor(private CatalogsAdmServices: CatalogsAdmService) {
  }
  displayedColumns: string[] = ['nombreCompleto', 'nacionalidad', 'fechaNacimiento', 'edad', 'rfc', 'curp', 'nss', 'detail3'];
  dataSource = [];

  ngOnInit() {
    this.getEmpleados();
  }

  getEmpleados() {
    this.CatalogsAdmServices.getEmploye().subscribe((res) => {
      this.dataSource = res;
    });

  }

  goDetailProyect(element) {
    if (element) {
      sessionStorage.setItem('empleado-detail', JSON.stringify(element));
      this.responseChildren.emit({value: 10});
    }
  }
  /*getUser() {
    if (sessionStorage.getItem('user')) {
      let user = JSON.parse(sessionStorage.getItem('user'));
      if (user.rol !== 3) {
        this.getProyects();
      } else {
        this.getProyects();
      }
    } else {
      this.getProyects();
    }
  }

  getProyectsbyRol() {
    this.ProyectoService.getProyectsbyRol(4).subscribe((res) => {
        this.proyects = res;
    });
  }*/



}
