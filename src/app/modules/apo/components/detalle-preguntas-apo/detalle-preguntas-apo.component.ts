import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import Swal from "sweetalert2";
import {ProyectoApoService} from '../../services/proyecto-apo.service';


@Component({
  selector: 'app-detalle-preguntas-apo',
  templateUrl: './detalle-preguntas-apo.component.html',
  styleUrls: ['./detalle-preguntas-apo.component.scss']
})
export class DetallePreguntasApoComponent implements OnInit {
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
  constructor(private ProyectService: ProyectoApoService) { }

  ngOnInit() {
    this.getProyects();
  }

  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {

      this.proyects = res;
    });
  }

  sendCuestionario() {
    Swal({
      title: '',
      text: 'Seguro que quieres enviar las actividades a los participantes',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,enviar',
      cancelButtonText: 'No'
    }).then((result) => {
      if(result.value){
        this.ProyectService.sendCuestionario(this.proyect.idProyecto).subscribe(res => {
          Swal(
            'Listo.',
            'Las actividades se enviaron correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        });
      }
    });
  }



}
