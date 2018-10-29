import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../../services/proyecto.service';

@Component({
  selector: 'app-organigrama',
  templateUrl: './organigrama.component.html',
  styleUrls: ['./organigrama.component.scss']
})
export class OrganigramaComponent implements OnInit {
  proyects = [];
  proyect = {};
  mobile = false;
  levels = []
  constructor(private ProyectService: ProyectoService) {
  }

  ngOnInit() {
    this.getProyects();
  }
  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }
  getOrganigrama() {
    this.ProyectService.getOrganigrama(this.proyect['idProyecto']).subscribe( (res)  => {
      this.levels = res;
      console.log(this.levels);


    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  getClass(data) {
    if (data.length > 8 ) {
      return 'col-sm-1';
    }
    if (data.length > 4 && data.length < 8) {
      return 'col-sm-2';
    }
    if (data.length > 0 && data.length < 4) {
      return 'col-sm-3';
    }
  }

}
