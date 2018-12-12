import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import {browser} from 'protractor';
import {LanguageModalAdmComponent} from '../../../adm/components/language-modal-adm/language-modal-adm.component';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {EncuestaModalEvdComponent} from '../encuesta-modal-evd/encuesta-modal-evd.component';
import {laboral_interface} from '../../../adm/components/labor-adm/labor-adm.component';
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from '@angular/router';

export interface quiz_interface {
  factor: string;
  descripcion: string;
  tipo: boolean;
}

@Component({
  selector: 'app-encuesta-evd',
  templateUrl: './encuesta-evd.component.html',
  styleUrls: ['./encuesta-evd.component.scss']
})
export class EncuestaEvdComponent implements OnInit {

  valor = '';

  @Output() responseChildren = new EventEmitter();

  constructor(private ProyectoEvdServices: ProyectoEvdService, public dialog: MatDialog, private router: Router, private route: ActivatedRoute) {
  }

  mobile = false;
  temas = [{
    nombre: 'Imagen'
  }];
  preguntas = [];
  token = '';
  dataSource = [{
    'nom': 1,
    'enunciado': '¿Como esta México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },{
    'nom': 2,
    'enunciado': '¿Como ves a México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },{
    'nom': 3,
    'enunciado': '¿Te gusta  México?',
    'valor1': 0,
    'valor2': 0,
    'valor3': 0,
    'valor4': 0,
    'valor5': 0


  },
  ];
  displayedColumns: string[] = [
    'ENUNCIADO',
    '1',

  ];

  ngOnInit() {
    this.getToken();
  }

  returnEmoji(id) {

    switch (id) {
      case 1:
        if (this.mobile) {
          return { id: 'sob', skin: 3, size: 16 };
        } else {
          return { id: 'sob', skin: 3, size: 24 };
        }
      case 2 :
        if (this.mobile) {
          return { id: 'cry', skin: 3 };
        } else {
          return { id: 'cry', skin: 3 };
        };
      case 3:
        if (this.mobile) {
          return { id: 'confused', skin: 3 };
        } else {
          return { id: 'confused', skin: 3 };
        }
      case 4:
        if (this.mobile) {
          return { id: 'grinning', skin: 3 };
        } else {
          return { id: 'grinning', skin: 3 };
        }
      case 5:
        if (this.mobile) {
          return { id: 'hugging_face', skin: 3 };
        } else {
          return { id: 'hugging_face', skin: 3 };
        }



    }

  }

  getToken() {
    this.route.params.subscribe(res => {
      this.token = res['token'];
      this.ProyectoEvdServices.getPreguntasParticipante(this.token).subscribe(res => {
        console.log(res);
        this.preguntas = res;
      });
    });
  }


  updateValor(question) {
    console.log(question);
    this.ProyectoEvdServices.updatePregunta(question).subscribe((res) => {
      console.log(res);
    });

  }

  FinishCuestionario() {
    Swal({
      title: '',
      text: 'Seguro que quieres finalizar el cuestionario',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, finalizar',
      cancelButtonText: 'No, seguir contestando'
    }).then((result) => {
      if (result.value) {
        Swal(
          'Listo.',
          'El cuestionario se finalizo correctamente',
          'success'
        ).then(() => {
          this.router.navigate(['']);
        });
      }
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 4;
    }

  }



}
