import { Component, OnInit } from '@angular/core';
import {Proyecto} from '../../models/proyecto';

@Component({
  selector: 'app-detalle-preguntas',
  templateUrl: './detalle-preguntas.component.html',
  styleUrls: ['./detalle-preguntas.component.scss']
})
export class DetallePreguntasComponent implements OnInit {

  temas = [
    {
      nombre:'Imagen',
      preguntas: [
        {
          enunciado: '¿Que tal tu dia?'
        },
        {
          enunciado: '¿Como estas?'
        }
      ]
    }
  ];
  constructor() { }

  ngOnInit() {
  }

}
