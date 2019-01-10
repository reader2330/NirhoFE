import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatTableDataSource} from '@angular/material';
import {LoginService} from '../../clb/services/login.service';


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
  vacantes = new MatTableDataSource();
  showTable = false;
  displayVacantes = ['Vacante', 'numeroVacantes', 'checkCandidato'];
  hasCandidato = false;
  hasGerente = false;
  expandedElement: null;
  user = {};
  solicitante = {};
  constructor(private ReclutamientoServices: ReclutamientoService, private Login: LoginService) { }

  ngOnInit() {
    if (sessionStorage.getItem('Candidato')) {
      this.hasCandidato = true;
    }
    this.getVacantes();
    this.getUser();
  }
  getVacantesBySolicitante() {
    this.ReclutamientoServices.getVacantesBySolicitante().subscribe(res => {
      this.vacantes = res;
    });
  }

  getVacantes() {
    this.ReclutamientoServices.getVacantes().subscribe(res => {
      this.vacantes.data = res;
      setTimeout(() => {
        this.showTable = true;
      }, 1000);

    });
  }
  removeVacante(element) {
    console.log(element);
    this.vacantes.data.map((item, i) => {
      if (item['id'] === element.id) {
        this.vacantes.data.slice(i, 1);
      }
    });
    this.ReclutamientoServices.removeVacante(element.id).subscribe(res => {
    });
  }
  setVacante(element) {
    let candidato = JSON.parse(sessionStorage.getItem('Candidato'));
    this.ReclutamientoServices.setVacante(candidato['id'], element.id).subscribe(res => {
      console.log(res);
    });
  }
  applyFilter(filterValue: string) {
    this.vacantes.filter = filterValue.trim().toLowerCase();
  }
  getUser() {
    this.Login.getUser().subscribe(res => {
      this.user = res;
    });
  }


}
