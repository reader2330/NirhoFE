import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';
import Swal from "sweetalert2";


@Component({
  selector: 'app-detalle-preguntas',
  templateUrl: './detalle-preguntas.component.html',
  styleUrls: ['./detalle-preguntas.component.scss']
})
export class DetallePreguntasComponent implements OnInit {
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
  constructor(private ProyectService: ProyectoService) { }

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
        Swal(
          'Listo.',
          'Los cuestionarios se enviaron correctamente',
          'success'
        ).then(() => {
          this.response.emit({value: 1});
        });
      }
    });
  }



}
