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
  totales = [0, 0, 0, 0, 0];
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
    this.createCharts = false;
    this.options = [];
    this.pies = [];
    this.data = [];
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
        xAxis: {
          categories : ['ML', 'M', 'R', 'B', 'MB'],
          title: {
            text: 'Respuesta'
          }
        },
        yAxis: {
          min: 0,
        },
        series: [{
          name: 'Total',
          data: []
        }]
      };
      for(let res of data ) {
        console.log(res);
        this.totales[0] += res.numResp1;
        this.totales[1] += res.numResp2;
        this.totales[2] += res.numResp3;
        this.totales[3] += res.numResp4;
        this.totales[4] += res.numResp5;
      }
      option.series[0].data = this.totales;
      console.log(this.options);
      this.options.push(option);
      this.totales = [0,0,0,0,0];
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
