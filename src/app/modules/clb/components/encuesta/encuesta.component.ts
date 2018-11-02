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
    nombre: 'prueba'
  }];
  token = "";

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
