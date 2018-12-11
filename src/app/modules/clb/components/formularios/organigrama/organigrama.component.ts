import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoService} from '../../../services/proyecto.service';


@Component({
  selector: 'app-organigrama',
  templateUrl: './organigrama.component.html',
  styleUrls: ['./organigrama.component.scss']
})
export class OrganigramaComponent implements OnInit {
  proyects = [];
  loadOrganigrama = false;
  @Output() responseChildren = new EventEmitter();
  proyect = {};
  mobile = false;
  levels = {
    nombre: '',
    puesto: '',
    subordinados: []
  };
  level1 = {
    participantes: []
  };
  level2 = {};
  level3 = {};
  level4 = {};
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
      this.responseChildren.emit({key: '1'} );
      console.log(res);
      this.loadOrganigrama = true;
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

  showMapa(person) {
    if (person && person.subordinados.length) {
      return true;
    } else {
      return false;
    }
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
