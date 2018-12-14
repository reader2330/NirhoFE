import {Component, EventEmitter, OnInit, Output} from '@angular/core';


import {MatDialog, MatTableDataSource} from '@angular/material';

import {laboral_interface} from '../../../adm/components/labor-adm/labor-adm.component';
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from '@angular/router';
import {Proyecto360Service} from '../../services/proyecto360.service';



@Component({
  selector: 'app-encuesta-evd',
  templateUrl: './encuesta-eva360.component.html',
  styleUrls: ['./encuesta-eva360.component.scss']
})
export class EncuestaEva360Component implements OnInit {

  valor = '';

  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoEvdServices: Proyecto360Service, public dialog: MatDialog, private router: Router, private route: ActivatedRoute) {
  }
  goPreguntas = false;
  mobile = false;
  temas = [{
    nombre: 'Imagen'
  }];
  preguntas = [];
  token = '';
  opciones = [];
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
    'Participante',
    'BR',
    'MR',
    'R',
    'RS',
    'E'
  ];

  ngOnInit() {
    this.getToken();
  }



  getToken() {
    this.route.params.subscribe(res => {
      this.token = res['token'];
      this.ProyectoEvdServices.getPreguntasParticipante(this.token).subscribe(res => {
        console.log(res.length);
        this.preguntas = res;
        this.preguntas.pop();
        let auxOpciones = [];
        for(let cuestionario of this.preguntas) {
          let i = 0;
          auxOpciones = [];
            for(let question of cuestionario.cuestionarioParticipantes){
              if (question.opciones && i === 0) {
              for (let opt of  question['opciones']) {
                auxOpciones.push(opt);
                i++;
              }
              this.opciones.push(auxOpciones);
              }
          }
        }
        this.goPreguntas = true;

      });
    });
  }


  updateValor(question) {
    console.log(question);
    this.ProyectoEvdServices.updatePregunta(question).subscribe((res) => {
      console.log(res);
    });

  }

  FinishCuestionario() {
    Swal({
      title: '',
      text: 'Seguro que quieres finalizar el cuestionario',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, finalizar',
      cancelButtonText: 'No, seguir contestando'
    }).then((result) => {
      if (result.value) {
        Swal(
          'Listo.',
          'El cuestionario se finalizo correctamente',
          'success'
        ).then(() => {
          this.router.navigate(['']);
        });
      }
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 4;
    }

  }
  goAvatarEditing() {
    this.router.navigate(['avatar-edit', 'EVD']);
  }



}
