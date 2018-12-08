import { Component, OnInit } from '@angular/core';
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-participantes-selector',
  templateUrl: './participantes-selector.component.html',
  styleUrls: ['./participantes-selector.component.scss']
})
export class ParticipantesSelectorComponent implements OnInit {
  mobile = false;
  participante = {
    idParticipante: 0
  };
  proyect = {
    idProyecto: 0
  };
  showParticipantes = false;
  proyects = [];
  particpantes = [];
  ampliaciones = [];
  showTable = false;
  displayedColumns: string[] = [
    'OBJETIVO PUESTO',
    'FUNCIONES',
    'ACTIVIDADES',
    'TIEMPO',
    'F.E.V.',


  ];
  constructor(private  ProyectoApoServices: ProyectoApoService) { }

  ngOnInit() {
    this.getProyectos();
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  goToParticipantes() {
    this.ProyectoApoServices.getParticipantes(this.proyect['idProyecto']).subscribe(res => {
      console.log(res);
      this.particpantes = res;
      this.showParticipantes = true;
    });
  }
  getProyectos() {
    this.ProyectoApoServices.getProyects().subscribe(res => {
      this.proyects = res;
    });
  }

  goToRelacion() {
    console.log(this.participante);
    this.ampliaciones = [];
    this.showTable = false;
    this.ProyectoApoServices.getAmpliacion(this.participante['id']).subscribe(res => {
      this.ampliaciones = res;
      this.showTable = true;
    });
  }

}
