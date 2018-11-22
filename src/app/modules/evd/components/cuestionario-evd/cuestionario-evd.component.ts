import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Pregunta} from '../../../clb/models/pregunta';
import Swal from "sweetalert2";
import {ProyectoEvdService} from '../../services/proyecto-evd.service';

@Component({
  selector: 'app-cuestionario-evd',
  templateUrl: './cuestionario-evd.component.html',
  styleUrls: ['./cuestionario-evd.component.scss']
})
export class CuestionarioEvdComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  selectProyect = false;
  proyects = [];
  proyect = {};
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
  tema = '';
  descripcion= '';

  constructor(private ProyectoEvdServices: ProyectoEvdService) { }

  ngOnInit() {
    this.getProyects();
  }

  showPreguntas() {
    this.load = true;
    this.ProyectoEvdServices.getPreguntas(this.proyect['idProyecto']).subscribe((res) => {
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
              this.selectPregunta.push(pregunta);
            }
          }
        }

        console.log(this.selectPregunta);
        let data = {
          idProyecto: this.proyect['idProyecto'],
          lista: this.selectPregunta
        };
        console.log(data);
        this.ProyectService.savePreguntas(data).subscribe((res) => {
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

  getProyects() {
    this.ProyectoEvdServices.getProyects().subscribe((res) => {

      this.proyects = res;
    });
  }

  getPreguntas() {
    console.log(this.tema);
    let id = this.tema['idTema'];
    this.ProyectoEvdServices.getPreguntas(id).subscribe((res) => {
      this.preguntas = res;
      for (let pregunta of this.preguntas) {
        pregunta.select = false;
      }
    });
  }

  addPreguntar(tema) {
    let enunciado = {...this.questionNew};
    let question = {...tema.preguntas[tema.preguntas.length - 1]};
    console.log("question", question)
    question['enunciado'] = enunciado.enunciado;
    question['select'] = true;
    question['idPregunta'] = question['idPregunta'] + 1;
    tema.preguntas.push(question);
    console.log(tema.preguntas);
    this.questionNew.enunciado = '';
  }

  addCompetencia() {
    this.temas.push({
      tema: {
        nombre: this.tema,
        descripcion: this.descripcion
      }
    });
  }

}
