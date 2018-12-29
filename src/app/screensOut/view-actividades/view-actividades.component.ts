import { Component, OnInit } from '@angular/core';
import {ProyectoApoService} from '../../modules/apo/services/proyecto-apo.service';
import {ActivatedRoute} from '@angular/router';
import {Actividad} from '../models/actividad';
import {environment} from '../../../environments/environment';
import {ImagenesModalComponent} from '../../modules/modal/imagenes-modal/imagenes-modal.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-view-actividades',
  templateUrl: './view-actividades.component.html',
  styleUrls: ['./view-actividades.component.scss']
})
export class ViewActividadesComponent implements OnInit {
  values = [0, 1, 2, 3, 4, 5];
  participante = {
    nombres: '',
    aPaterno: '',
    aMaterno: '',
    puesto: ''
  };
  newActividad = {};
  nirhoColor = '#A1B712';
  showGraphs = false;
  data = {
    url: ''
  };
  showTable = false;
  azul = 'blue';
  isBoss = false;
  urlMensagge = environment.urlNG + 'assets/Mensajes/APO-2.PNG';
  ampliaciones = [];
  displayedColumns: string[] = [
    'FUNCIONES',
    'TIEMPO',
    'F.E.V.',
    'cantidadMeta',
    'metaKpi',
    'detalle1',
    'detalle2',
  ];
  graphs = [];
  chart1 = {
    chart: {
      type: 'column',
      options3d: {
        enabled: true,
        alpha: 30,
        beta: 0,
        depth: 38,
        viewDistance: 25
      }
    },
    title: {
      text: 'Funciones'
    },
    subtitle: {
      text: 'Calificacion por funcion'
    },
    xAxis: {
      categories: [

      ],
      title: 'Funciones'

    },
    yAxis: {
      min: 0,
    },
    plotOptions: {
      column: {
        pointPadding: 0.2,
        borderWidth: 0
      }
    },
    series: [{
      name: 'Calificación',
      data: []
    }]
  };
  metas = [
    {
      title : 'Hola',
      actividades: [
              {
                start :  new Date('2018,01,25'),
                name: 'Hola perro',
                fecha: new Date('2018,02,20'),
                calificacion: 0
              },
              {
                start: new Date()
              },
              {
                start : new Date(2018, 10, 30)
              }]
      },
    {
      title: 'adios',
      actividades: [
        {
          start :  new Date('2018,12,25'),
        },
        {
          start: new Date()
        },
        {
          start : new Date(2018, 10, 30),
          name: ''
        }

      ]
    },
    {
      title: 'nuevo',
      actividades: [
        {
          start :  new Date('2018,12,25')
        },
        {
          start: new Date()
        },
        {
          start : new Date(2018, 10, 30)
        }

      ]
    }];
  constructor(private ProyectServices: ProyectoApoService, private route: ActivatedRoute,  public dialog: MatDialog) { }

  ngOnInit() {
    this.Imagenes(1);
    this.route.params.subscribe(res => {
      this.ProyectServices.getToken(res['token']).subscribe(res2 => {
        console.log(res2);
        this.isBoss = res2['jefe'];
        this.participante = res2['participante'];
        this.metas = res2['participante']['ampliaciones'];
        this.getCalificaciones(res2['participante']['idParticipante']);
        this.getActividadesParticipante(res2['participante']['idParticipante']);
        for(let met of this.metas) {
          for (let act of met['funciones']) {
            if (act['actividades'].length < 5) {
              act['actividades'].push({
                name: ''
              });
            }
          }
        }
        this.ampliaciones = this.metas[0]['funciones'];
        this.showTable = true;
      });
    });

  }

  currentUser() {
    if (this.isBoss) {
      return true;
    } else {
      return false;
    }
  }

  generateActividad(meta) {
    let act = new Actividad(null, this.newActividad['nombre'], new Date(), this.newActividad['fechaVencimiento'], null, null, true);
    meta['actividades'].pop();
    meta['actividades'].push(act);
    this.ProyectServices.guardaActividad(meta['id'], act).subscribe(res => {
    });
    if (meta['actividades'] && meta['actividades'].length < 5) {
      meta['actividades'].push({
        start: new Date(),
        name: '',
        fecha: ''
      });
    }

    this.newActividad['nombre'] = '';
    this.newActividad['fechaVencimiento'] = '';

  }

  updateObjetivos(element) {
    element['actividades'].pop();
    this.ProyectServices.updateObjetivos(element).subscribe(res => {
      console.log(res);
      element['actividades'].push({
        name: ''
      });
    });
  }

  updateCalificaionActividad(act, fuc) {
    this.ProyectServices.updateCalificacionActividad( fuc['id'], act['id'], act['calificacion']).subscribe(res =>{
      console.log(res);
    });
  }

  openDialogStart(data, time) {
    this.dialog.open(ImagenesModalComponent, {
      data: data,
      height: '500px',
      width: '700px'
    });

    setTimeout(() => {
      this.dialog.closeAll();
    }, time);
  }

  Imagenes(num) {
    switch (num) {
      case 1:
        this.data.url =   environment.urlNG + 'assets/Mensajes/APO-1.png';
        this.openDialogStart(this.data,3000);
        break;
      case 2:
        this.data.url =   environment.urlNG + 'assets/Mensajes/APO-3.PNG';
        this.openDialogStart(this.data,6000);
    }



  }
  getCalificaciones(id) {
    this.ProyectServices.getCalificaciones(id).subscribe(res => {
      console.log(res);
      let opt = {
        chart: {
          type: 'columng',
          options3d: {
            enabled: true,
            alpha: 30,
            beta: 0,

          }
        },
        title: {
          text: 'Funciones'
        },
        subtitle: {
          text: 'Calificacion por funcion'
        },
        xAxis: {
          categories: [],
          title: 'Funciones'

        },
        yAxis: {
          min: 0,
        },
        plotOptions: {
          column: {
            pointPadding: 0.2,
            borderWidth: 0
          }
        },
        series: [{
          name: 'Calificación',
          type: 'column',
          data: []
        }]
      };
      for (let fuc of res['funciones']) {
        opt.series[0].data.push(fuc['calificacion']);
        opt.xAxis.categories.push(fuc['funcion']);
      }
      /*opt.series.push({
        name: 'Promedio',
        type: 'spline',
        data: [res['promedio']]
      });*/
      this.graphs.push(opt);
      this.showGraphs = true;

    });
  }
  getActividadesParticipante(id) {
    this.ProyectServices.getStatusActividades(id).subscribe(res => {
      console.log(res);
      let resultado = [];
      for (let ul in res ){
        resultado.push(res[ul]);
      }
      let opt = {
        chart: {
          type: 'column',
          options3d: {
            enabled: true,
            alpha: 30,
            beta: 0,
            depth: 38,
            viewDistance: 25
          }
        },
        title: {
          text: 'Funciones'
        },
        subtitle: {
          text: 'Estado de las actividades'
        },
        xAxis: {
          categories: [],
          title: 'Funciones'

        },
        yAxis: {
          min: 0,
        },
        plotOptions: {
          column: {
            pointPadding: 0.2,
            borderWidth: 0
          }
        },
        series: []
      };
      opt.series.push({
        name: 'Terminadas',
        data: []
      });
      opt.series.push({
        name: 'Activas',
        data: []
      });
      for (let ul of resultado) {
        opt.xAxis.categories.push(ul['funcion']);
        opt.series[0].data.push(ul['terminadas']);
        opt.series[1].data.push(ul['activas']);
      }
      this.graphs.push(opt);
    });
  }

}
