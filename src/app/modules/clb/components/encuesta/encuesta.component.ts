import { Component, OnInit } from '@angular/core';
import {ProyectoService} from '../../services/proyecto.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';
import Swal from "sweetalert2";

@Component({
  selector: 'app-encuesta',
  templateUrl: './encuesta.component.html',
  styleUrls: ['./encuesta.component.scss']
})
export class EncuestaComponent implements OnInit {
  mobile = false;
  temas = [{
    nombre: 'Imagen'
  }];
  goPreguntas = false;
  token = '';
  dataSource = [

  ];
  empty = false;
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
  };
  ngOnInit() {
    this.getToken();
  }

  getToken() {
      this.route.params.subscribe((res) => {
        this.token = res['token'];
        this.ProyectServices.getPreguntasParticipante(this.token).subscribe((res) => {
          if (res) {
            this.temas = res;
            this.dataSource = [];
            for (let tema of this.temas) {
              this.dataSource.push(tema);
            }
            this.goPreguntas = true;
          }
        },
          (err) => {
              this.empty = true;
            Swal({
              title: 'Lo siento!',
              text: 'El proyecto ha finalizado, ya no puede contestar su cuestionario',
              type: 'warning',
            }).then(() => {
              this.router.navigate(['']);
            });
          }); });
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
    this.ProyectServices.updatePregunta(question).subscribe((res) => {
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
          Swal(
            'Muchas gracias por tu participación.',
            'Tu información será administrada de manera\n' +
            'totalmente confidencial y por profesionales. Muy\n' +
            'pronto estarémos compartiendo contigo los\n' +
            'resultados de manera global y nuestros planes de\n' +
            'desarrollo.',
            'success').then(() => {
            this.router.navigate(['']);
          });

        });
      }
      });
  }




}
