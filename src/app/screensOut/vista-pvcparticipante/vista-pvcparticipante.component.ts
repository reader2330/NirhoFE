import { Component, OnInit } from '@angular/core';
import {ProyectoPVCService} from '../../modules/pvc/services/proyectoPVC.service';
import {ActivatedRoute} from '@angular/router';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-vista-pvcparticipante',
  templateUrl: './vista-pvcparticipante.component.html',
  styleUrls: ['./vista-pvcparticipante.component.scss']
})
export class VistaPVCParticipanteComponent implements OnInit {
  token = '';
  urlMensagge = environment.urlNG + 'assets/Mensajes/APO-2.PNG';
  participante = {};
  options = [
    {
      key: 1,
      name: 'No apto'
    },
    {
      key: 2,
      name: 'En desarrollo'
    },
    {
      key: 3,
      name: 'Apto'
    }
  ];
  constructor(private ProyectServices: ProyectoPVCService,  private route: ActivatedRoute) { }
  ngOnInit() {
    this.getToken();
  }


  getToken() {
    this.route.params.subscribe(res => {
      this.token = res['token'];
      this.ProyectServices.getToken(this.token).subscribe(res2 => {
        this.participante = res2;
        console.log(JSON.stringify(this.participante))
      });
    });
  }

  guardarCalificacion(element) {
    this.ProyectServices.CalificarConocimiento(element.id, element.calificacion).subscribe(res => {
      console.log(res);
    });
  }

}
