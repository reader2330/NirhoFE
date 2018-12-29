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
  participante = {
    nombre: 'Nombre de prueba',
    puestoActualTecnico: {
      puesto: 'Desarrollador java JR',
      area: 'Desarrollo',
      esfera: 'Desarrollo en Backend',
      nivel: 'JR',
      especialidad: 'Java',
      conocimientos: [
        'Relación de base de datos',
        'Rest FULL API',
        'Cambio en los backs',
        'DAO y DTO'
      ]
    },
    puestoSiguienteTecnico: {
      puesto: 'Desarrollador java MD',
      area: 'Desarrollo',
      esfera: 'Desarrollo en Backend',
      nivel: 'MD',
      especialidad: 'Java',
      conocimientos: [
        'Relación de base de datos',
        'Rest FULL API',
        'Cambio en los backs',
        'DAO y DTO',
        'Cambio en redes',
        'cambio en todos las apps',
        'cambio de pruebas negras'
      ]
    }
  };
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

}
