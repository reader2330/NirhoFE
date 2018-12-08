import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-poll-status-apo',
  templateUrl: './poll-status-apo.component.html',
  styleUrls: ['./poll-status-apo.component.scss']
})
export class PollStatusApoComponent implements OnInit {

  mobile = false;
  evaluador = [];
  proyects = [];
  stages = [{
    id: 1,
    name: 'Capturado',
    completed: true
  }, {
    id: 2,
    name: 'Asignado',
    completed: true
  }, {
    id: 3,
    name: 'Carga HC',
    completed: false
  }, {
    id: 4,
    name: 'Análisis',
    completed: false
  }, {
    id: 5,
    name: 'Terminado',
    completed: false
  }];
  showStatus = false;
  proyect = {};

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private ProyectoApoService: ProyectoApoService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait
    ]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
        this.checkMobileCols();
      } else {
        this.mobile = false;
        this.checkMobileCols();
      }
    });
  }

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }
  getProyects() {
    this.ProyectoApoService.getProyects().subscribe(res => {
      console.log(res);
      this.proyects = res;
    });
  }

  ngOnInit() {
    this.getProyects();
  }
  goToEstatus() {
    this.showStatus = false;
    this.ProyectoApoService.getStatus(this.proyect['idProyecto']).subscribe(res => {
      this.stages.forEach(item => {
        if (res['idEstatus'] >= item.id) {
          item.completed = true;
        }
      });
      this.showStatus = true;
    });
  }

}
