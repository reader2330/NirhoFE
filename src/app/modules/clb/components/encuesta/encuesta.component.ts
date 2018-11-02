import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-encuesta',
  templateUrl: './encuesta.component.html',
  styleUrls: ['./encuesta.component.scss']
})
export class EncuestaComponent implements OnInit {

  temas = [{
    nombre: 'Imagen'
  }];
  token = '';
  dataSource = [{
    'nom': 1,
    'enunciado': '¿Como esta México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },{
    'nom': 2,
    'enunciado': '¿Como ves a México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },{
    'nom': 3,
    'enunciado': '¿Te gusta  México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },
  ];
  displayedColumns: string[] = [
    '#',
    'ENUNCIADO',
    '1',
    '2',
    '3',
    '4',
    '5'



  ];

  constructor(private ProyectServices: ProyectoService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getToken();
  }


  getToken() {
      this.route.params.subscribe((res) => {
        this.token = res['token'];
        this.ProyectServices.getPreguntasParticipante(this.token).subscribe((res) => {
          console.log(res);
        });
      });

  }




}
