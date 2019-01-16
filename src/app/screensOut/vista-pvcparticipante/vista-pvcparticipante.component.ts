import { Component, OnInit } from '@angular/core';
import {ProyectoPVCService} from '../../modules/pvc/services/proyectoPVC.service';
import {ActivatedRoute} from '@angular/router';
import {environment} from '../../../environments/environment';
import {ImagenesModalComponent} from '../../modules/modal/imagenes-modal/imagenes-modal.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-vista-pvcparticipante',
  templateUrl: './vista-pvcparticipante.component.html',
  styleUrls: ['./vista-pvcparticipante.component.scss']
})
export class VistaPVCParticipanteComponent implements OnInit {
  token = '';
  urlMensagge = environment.urlNG + 'assets/Mensajes/PVC-1.PNG';
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
  data = {
    url: ''
  };
  constructor(private ProyectServices: ProyectoPVCService,  private route: ActivatedRoute, private dialog: MatDialog) { }
  ngOnInit() {
    this.getToken();
    this.Imagenes(1);
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

  openDialogStart(data, time) {
    this.dialog.open(ImagenesModalComponent, {
      data: data,
      height: '500px',
      width: '700px'
    });

    setTimeout(() => {
      this.dialog.closeAll();
    }, time);
  }

  Imagenes(num) {
    switch (num) {
      case 1:
        this.data.url =   environment.urlNG + 'assets/Inicial/PVC.PNG';
        this.openDialogStart(this.data,3000);
        break;
      case 2:
        this.data.url =   environment.urlNG + 'assets/Mensajes/SALIDA.PNG';
        this.openDialogStart(this.data,6000);
    }



  }

}
