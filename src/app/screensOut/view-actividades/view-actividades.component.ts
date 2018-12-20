import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-actividades',
  templateUrl: './view-actividades.component.html',
  styleUrls: ['./view-actividades.component.scss']
})
export class ViewActividadesComponent implements OnInit {
  values = [0, 1, 2, 3, 4, 5];
  newActividad = {};
  nirhoColor = '#A1B712';
  azul = 'blue';
  participante = {
    isBoss: false
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
  constructor() { }

  ngOnInit() {
  }

  currentUser() {
    if (this.participante.isBoss) {
      return true;
    } else {
      return false;
    }
  }

  generateActividad(meta) {
    console.log(meta);

    meta['actividades'].pop();
    meta['actividades'].push({
      start: new Date(),
      name: this.newActividad['nombre'],
      fecha: this.newActividad['fechaVencimiento']
    });
    meta['actividades'].push({
      start: new Date(),
      name: '',
      fecha: ''
    });
    this.newActividad['nombre'] = '';
    this.newActividad['fechaVencimiento'] = '';

  }

}
