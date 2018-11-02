import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';
import {Pregunta} from '../../models/pregunta';
import Swal from "sweetalert2";


@Component({
  selector: 'app-cuestionario-select',
  templateUrl: './cuestionario-select.component.html',
  styleUrls: ['./cuestionario-select.component.scss']
})
export class CuestionarioSelectComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  selectProyect = false;
  proyects = [];
  proyect = {};
  temas = [];
  tema = {};
  select = [];
  preguntas: Pregunta[] = [];
  selectPregunta: Pregunta[] = [];
  constructor( private ProyectService: ProyectoService) { }

  ngOnInit() {
    this.getProyects();
    this.getTemas();
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
        for (let pregunta of this.preguntas) {
          if (pregunta.select) {
            delete pregunta.select;
            this.selectPregunta.push(pregunta);
          }
        }
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
            this.response.emit({key: 1});
          });
        }, (err) => {
          console.log(err);
          Swal(
            'Algo salio mal.',
            'No se pudo guarda la información',
            'error'
          ).then(() => {
            this.response.emit({key: 1});
          });
        });
      }
    });
  }

  getTemas() {
    this.ProyectService.getTemas().subscribe((res) => {

      this.temas = res;
    });
  }
  getPreguntas() {
    console.log(this.tema);
    let id = this.tema['idTema'];
    this.ProyectService.getPreguntas(id).subscribe((res) => {
      this.preguntas = res;
      for (let pregunta of this.preguntas) {
        pregunta.select = false;
      }
    });
  }



  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {

      this.proyects = res;
    });
  }
  changeSelectProyect(evt){
    this.getPreguntas();
  }


}
