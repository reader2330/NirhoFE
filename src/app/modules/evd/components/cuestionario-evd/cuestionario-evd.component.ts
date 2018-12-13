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
  proyect = {
    idProyecto: undefined
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
  descripcion = '';


  constructor(private ProyectoEvdServices: ProyectoEvdService) { }

  ngOnInit() {
    this.getProyects();
  }

  showPreguntas() {
    this.load = true;
    this.ProyectoEvdServices.getPreguntasProyect(this.proyect['idProyecto']).subscribe((res) => {
      console.log(res);
      this.temas = res;
      this.load = false;
      this.save = true;

    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  sendCuestionario() {
    Swal({
      title: '',
      text: 'Seguro que quieres enviar los cuestionarios',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectoEvdServices.sendCuestionarios(this.proyect.idProyecto).subscribe((res) => {
          Swal(
            'Listo.',
            'Los cuestionarios se han enviado correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        }, (err) => {
          console.log(err);
          Swal(
            'Algo salio mal.',
            'No se pudieron los cuestionarios',
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

}
