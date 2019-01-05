import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {animate, state, style, transition, trigger} from '@angular/animations';


@Component({
  selector: 'app-vacante-table',
  templateUrl: './vacante-table.component.html',
  styleUrls: ['./vacante-table.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class VacanteTableComponent implements OnInit {
  vacantes = [];
  showTable = false;
  displayVacantes = ['Vacante', 'numeroVacantes', 'checkCandidato', 'detail1'];
  hasCandidato = false;
  hasGerente = false;
  expandedElement: null;
  constructor(private ReclutamientoServices: ReclutamientoService) { }

  ngOnInit() {
    if (sessionStorage.getItem('Candidato')) {
      this.hasCandidato = true;
    }
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
    this.vacantes.map((item, i) => {
      if (item.id === element.id) {
        this.vacantes.slice(i, 1);
      }
    });
    this.ReclutamientoServices.removeVacante(element.id).subscribe(res => {
      console.log(res);
    });
  }

  setVacante(element) {
    console.log(element);
    let candidato = JSON.parse(sessionStorage.getItem('Candidato'));
    this.ReclutamientoServices.setVacante(candidato['id'], element.id).subscribe(res => {
      console.log(res);
    });

  }


}
