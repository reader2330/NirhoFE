import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';

@Component({
  selector: 'app-vacante-table',
  templateUrl: './vacante-table.component.html',
  styleUrls: ['./vacante-table.component.scss']
})
export class VacanteTableComponent implements OnInit {
  vacantes = [];
  showTable = false;
  displayVacantes = ['Vacante', 'numeroVacantes', 'detail1'];
  constructor(private ReclutamientoServices: ReclutamientoService) { }

  ngOnInit() {
    this.getVacantes();
  }
  getVacantes() {
    this.ReclutamientoServices.getVacantes().subscribe(res => {
      console.log(res);
      this.vacantes = res;
      this.showTable = true;
    });
  }
  removeVacante(element) {
    console.log(element);
    this.ReclutamientoServices.removeVacante(element.id).subscribe(res => {
      console.log(res);
    });
  }


}
