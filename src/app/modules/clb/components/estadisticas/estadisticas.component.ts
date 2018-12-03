import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.scss']
})
export class EstadisticasComponent implements OnInit {
  mobile = false;
  proyects = [];
  proyect = {};
  options: Object;
  options2: Object;
  options3: Object;
  constructor(private ProyectService: ProyectoService) {
    this.options = {
      title : { text : 'Preguntas' },
      series: [{
        data: [29.9, 71.5, 106.4, 129.2],
      }]
    };
    this.options2 = {
      chart: {type: 'bar'},
      title : { text : 'Respuesta' },
      series: [{
        data: [29.9, 71.5, 106.4, 129.2],
      }]
    };
    this.options3 = {
      chart: {type: 'pie'},
      title : { text : 'Respuesta' },
      series: [{
        data: [29.9, 71.5, 106.4, 129.2],
      }]
    };
  }

  ngOnInit() {
    this.getProyects();
    this.getGraficas();
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

  getGraficas(){
    this.ProyectService.getGraficas(9).subscribe(res => {
      console.log(res);
    })
  }

}
