import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-bandeja-lista-apo',
  templateUrl: './bandeja-lista-apo.component.html',
  styleUrls: ['./bandeja-lista-apo.component.scss']
})
export class BandejaListaApoComponent implements OnInit {

  proyects: Proyecto[];
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoApoServices: ProyectoApoService) {
  }

  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'frecuenciaEval', 'detail3'];
  dataSource = [];

  ngOnInit() {
    this.getProyects();
  }

  getProyects() {

    this.ProyectoApoServices.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
      this.dataSource = this.proyects;
    });

  }

  goDetailProyect(element) {
    if (element) {
      sessionStorage.setItem('detail', JSON.stringify(element));
      this.responseChildren.emit({value: 10});

    }
  }

  getUser() {
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

  getFrecuencia(num) {

    if (num === 1) {
      return 'Mensual';
    }
    if (num === 2) {
      return 'Bimestral';
    }
    if (num === 3) {
      return 'Semestral';
    }
    if (num === 4) {
      return 'Anual';
    }
    if (num === 5 ) {
      return 'Unica vez';
    }

  }

}

