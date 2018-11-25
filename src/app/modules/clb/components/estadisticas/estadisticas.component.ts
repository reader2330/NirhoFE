import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';
import construct = Reflect.construct;

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.scss']
})
export class EstadisticasComponent implements OnInit {
  mobile = false;
  proyects = [];
  data = {};
  proyect = {};
  options = [];
  pies = [];
  createCharts = false;
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

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  getGraficas() {
    this.ProyectService.getGraficas(this.proyect['idProyecto']).subscribe(res => {
      this.data = res;
      if(res['datos']){
        this.constructoChars();
      }

    });
  }

  constructoChars() {
   /* this.options = {
      chart: {type: 'column'},
      title : { text : '' },
      series: [{
        data: [29.9, 71.5, 106.4, 129.2],
      }]
    }*/
    for ( let data of this.data['datos']) {
      let option = {
        chart: {type: 'column'},
        title: {text: data[0]['gaficaProyectoPK']['areaOrg'] },
        subtitle: {text: data[0]['tema']['nombre']},
        xAxis: {
          categories : ['ML', 'M', 'R', 'B', 'MB'],
          title: {
            text: 'Respuesta'
          }
        },
        yAxis: {
          min: 0,
          max: 5
        },
        series: [{
          name: 'Total',
          data: [data[0]['numResp1'] , data[0]['numResp2'], data[0]['numResp3'], data[0]['numResp4'], data[0]['numResp5']]
        }]
      };
      this.options.push(option);

    }
    let pies = {
      chart: {type: 'pie'},
      title: {text: 'Total de participantes' },
      subtitle: {text: 'Por área'},
      series: [{
        name : '#Participantes',
        colorByPoint: true,
        data: []
      }]
    };
    for (let pie of this.data['pastel']) {
      pies.series[0]['data'].push({
          name: pie.areaOranizacional,
          y: pie.numParticipantes
      });
    }
    this.pies.push(pies);
    console.log(this.pies);

    this.createCharts = true;

  }

}
