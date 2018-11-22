import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {Pregunta} from '../../models/pregunta';
import Swal from 'sweetalert2';
import {EnterprisesService} from '../../services/enterprises.service';


@Component({
  selector: 'app-cuestionario-select-irh',
  templateUrl: './cuestionario-select-irh.component.html',
  styleUrls: ['./cuestionario-select-irh.component.scss']
})
export class CuestionarioSelectIRHComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  selectProyect = false;
  entrepises = [];
  entripise = {
    id:undefined
  };
  temas = [];
  tema = {};
  load = false;
  save = false;
  select = [];
  preguntas: Pregunta[] = [];
  selectPregunta: Pregunta[] = [];
  questionNew = {
    enunciado : ''
  };
  constructor( private EnterprisesService: EnterprisesService) { }

  ngOnInit() {
    this.getEntrepises();

  }
  showPreguntas() {
    this.load = true;
    console.log(this.entripise);
    this.EnterprisesService.getPreguntas(this.entripise['id']).subscribe((res) => {
      console.log(res);
      this.temas = res;
      this.save = true;
      for (let tema of this.temas) {
        for (let question of tema.preguntas) {
          question.select = false;
        }
      }
      this.load = false;
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }
  savePreguntas() {



    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        for (let tema of this.temas) {
          for (let pregunta of tema.preguntas) {
            if (pregunta.select) {
              delete pregunta.select;
              delete pregunta.idTema;
              this.selectPregunta.push(pregunta);
            }
          }
        }

        console.log(this.selectPregunta);
        let data = {
          idEmpresa: this.entripise['id'],
          finalizado: false,
          temas:[{
            nombre: "imagen",
            descripcion:"Tema 01 de Clima Laboral",
            status: true,
            preguntas: this.selectPregunta,
          }]
        };
        console.log(data);
        this.EnterprisesService.savePreguntas(data).subscribe((res) => {
          Swal(
            'Listo.',
            'La información se guardo correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        }, (err) => {
          console.log(err);
          Swal(
            'Algo salio mal.',
            'No se pudo guarda la información',
            'error'
          ).then(() => {

          });
        });
      }
    });
  }

  getPreguntas() {
    console.log(this.tema);
    let id = this.tema['idTema'];
    this.EnterprisesService.getPreguntas(id).subscribe((res) => {
      this.preguntas = res;
      for (let pregunta of this.preguntas) {
        pregunta.select = false;
      }
    });
  }



  getEntrepises() {
    this.EnterprisesService.getEnterprises().subscribe((res) => {
      console.log(res);
      this.entrepises = res;
    });
  }


  addPreguntar(tema) {
    let enunciado = {...this.questionNew};
    let question = {...tema.preguntas[tema.preguntas.length - 1]};
    question['enunciado'] = enunciado.enunciado;
    question['select'] = true;
    question['idPregunta'] = question['idPregunta'] + 1;
    tema.preguntas.push(question);
    console.log(tema.preguntas);
    this.questionNew.enunciado = '';
  }


}
