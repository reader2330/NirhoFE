import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {MatButtonModule} from '@angular/material/button';
import {ProyectoService} from '../../services/proyecto.service';
import {Proyecto} from '../../models/proyecto';
import {CatalogsService} from '../../services/catalogs.service';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';


@Component({
  selector: 'app-bandeja',
  templateUrl: './bandeja-adm.component.html',
  styleUrls: ['./bandeja-adm.component.scss']
})
export class BandejaAdmComponent implements OnInit {
  proyects: Proyecto[];
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoService: ProyectoService, private CatalogsAdmService: CatalogsAdmService) {
  }
  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'periodo', 'frecuenciaEval', 'detail3'];
  dataSource = [];




  ngOnInit() {

    this.getEmpleados();
  }
  getEmpleados() {

    this.CatalogsAdmService.getEmploye().subscribe((res) => {
      this.dataSource = res;
    });

  }

  goDetailProyect(element) {
    if (element) {
      sessionStorage.setItem('detail', JSON.stringify(element));
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
