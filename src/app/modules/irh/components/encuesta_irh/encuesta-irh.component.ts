import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';
import Swal from "sweetalert2";

@Component({
  selector: 'app-encuesta-irh',
  templateUrl: './encuesta-irh.component.html',
  styleUrls: ['./encuesta-irh.component.scss']
})
export class EncuestaIrhComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  temas = [{
    nombre: 'Imagen'
  }];
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

  constructor(private ProyectServices: ProyectoService, private route: ActivatedRoute, private router: Router, breakpointObserver: BreakpointObserver) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
      } else {
        this.mobile = false;
      }
    });
  }
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

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 4;
    }

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
  returnSize() {
    if (this.mobile) {
      return 16;
    } else {
      return 24;
    }
  }

  formatLabel(value: number | null) {
    if (!value) {
      return 0;
    }

    switch (value) {
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
        }
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

    return value;
  }

  updateValor(question) {
    console.log(question);
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
          this.response.emit({value: 1});
        });
      }
      });
  }




}