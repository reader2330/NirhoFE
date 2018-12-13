import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import {ProyectoEvdService} from '../../../evd/services/proyecto-evd.service';
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-organigrama-apo',
  templateUrl: './organigrama-apo.component.html',
  styleUrls: ['./organigrama-apo.component.scss']
})
export class OrganigramaApoComponent implements OnInit {
  loadOrganigrama = false;
  proyects = [];
  proyect = {};
  mobile = false;
  levels = [];
  level1 = {
    participantes: []
  };
  level2 = {};
  level3 = {};
  level4 = {};
  constructor(private ProyectService: ProyectoApoService) {
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
      console.log(res);
      this.loadOrganigrama = true;
      /*this.levels.sort(((a, b) => {
        if (a.nivel > b.nivel) {
          return 1;
        }
        if (a.nivel < b.nivel) {
          return -1;
        }
      }));
      console.log(this.levels);
      this.level1 = this.levels[0];
      this.level2 = this.levels[1];
      this.level3 = this.levels[2];
      this.level4 = this.levels[3];
      console.log(this.level1);
      console.log(this.level2);
      console.log(this.level3);



    });*/
    });
  }
  getColor(level) {

    let colors = [
      '#A1B712',
      '#b9d162',
      '#ccdd91',
      '#e0ebc2',

    ];
    let color = colors[level.nivel - 1];
    console.log(color);
    return colors[level.nivel - 1];

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