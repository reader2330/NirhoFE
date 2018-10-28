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
  constructor(private ProyectService:ProyectoService) { }

  ngOnInit() {
    this.getProyects();
  }
  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

}
