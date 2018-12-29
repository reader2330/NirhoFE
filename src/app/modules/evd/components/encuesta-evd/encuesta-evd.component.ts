import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import {MatDialog, MatTableDataSource} from '@angular/material';
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../../../../environments/environment';
import {ImagenesModalComponent} from '../../../modal/imagenes-modal/imagenes-modal.component';


@Component({
  selector: 'app-encuesta-evd',
  templateUrl: './encuesta-evd.component.html',
  styleUrls: ['./encuesta-evd.component.scss']
})
export class EncuestaEvdComponent implements OnInit {

  valor = '';
  data = {
    url: ''
  };

  @Output() responseChildren = new EventEmitter();
  constructor(private ProyectoEvdServices: ProyectoEvdService, public dialog: MatDialog, private router: Router, private route: ActivatedRoute) {
  }
  imgLogo = environment.urlNG + 'assets/Mensajes/EVD-2.png';
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
    this.Imagenes(1)
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
      this.ProyectoEvdServices.getPreguntasParticipante(this.token).subscribe(res2 => {
        this.preguntas = res2;
      });
    });
  }


  updateValor(question) {
    this.ProyectoEvdServices.updatePregunta(question.cuestionarioParticipantes[0]).subscribe((res) => {
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
          this.Imagenes(2)
          setTimeout(() => {
            window.location.href = 'http://nirho.com/';
          }, 6000);
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
        this.data.url =   environment.urlNG + 'assets/Mensajes/EVD-1.png';
        this.openDialogStart(this.data,2000);
        break;
      case 2:
        this.data.url =   environment.urlNG + 'assets/Mensajes/EVD-3.png';
        this.openDialogStart(this.data,6000);
    }

  }
  goAvatarEditing() {
    this.router.navigate(['avatar-edit', 'EVD']);
  }



}
