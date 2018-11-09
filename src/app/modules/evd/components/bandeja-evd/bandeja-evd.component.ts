import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';

@Component({
  selector: 'app-bandeja-evd',
  templateUrl: './bandeja-evd.component.html',
  styleUrls: ['./bandeja-evd.component.scss']
})
export class BandejaEvdComponent implements OnInit {

  proyects: Proyecto[];
  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoEvdServices: ProyectoEvdService) {
  }

  displayedColumns: string[] = ['nombre', 'empresa', 'empleados', 'participantes', 'periodo', 'frecuenciaEval', 'detail3'];
  dataSource = [];

  ngOnInit() {
    this.getProyects();
  }

  getProyects() {

    this.ProyectoEvdServices.getProyects().subscribe((res) => {
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

  getProyectsbyRol() {
    this.ProyectoEvdServices.getProyectsbyRol(4).subscribe((res) => {
      this.proyects = res;
    });
  }

}
