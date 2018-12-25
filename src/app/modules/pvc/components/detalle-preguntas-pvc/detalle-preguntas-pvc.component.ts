import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import Swal from "sweetalert2";
import {ProyectoPVCService} from '../../services/proyectoPVC.service';


@Component({
  selector: 'app-detalle-preguntas-pvc',
  templateUrl: './detalle-preguntas-pvc.component.html',
  styleUrls: ['./detalle-preguntas-pvc.component.scss']
})
export class DetallePreguntasPvcComponent implements OnInit {
  @Output() response = new EventEmitter();
  temas = [
    {
      nombre: 'Imagen',
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
  proyect = {
    idProyecto: null
  };
  proyects = [];
  load = false;
  showPreguntas = false;
  constructor(private ProyectService: ProyectoPVCService) { }

  ngOnInit() {
    this.getProyects();
  }

  getPreguntas() {
    this.load = true;
    this.ProyectService.getPreguntasProyect(this.proyect.idProyecto).subscribe((res)  => {
      console.log(res);
      this.temas = res;
      this.showPreguntas = true;
      this.load = false;
    });
  }
  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {

      this.proyects = res;
    });
  }

  sendCuestionario(){
    Swal({
      title: '',
      text: 'Seguro que quieres enviar los cuestionarios a los participantes',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,enviar',
      cancelButtonText: 'No'
    }).then((result) => {
      if(result.value){
        this.ProyectService.sendCuestionario(this.proyect.idProyecto).subscribe(res => {
          Swal(
            'Listo.',
            'Los cuestionarios se enviaron correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        });
      }
    });
  }



}
