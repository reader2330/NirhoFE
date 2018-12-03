import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';
import Swal from "sweetalert2";
import {EnterprisesService} from '../../services/enterprises.service';

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
  goQuestion = true;
  companys = [];
  entrepise = {
    id: 0
  };
  opts = [
    {name: 'SI', key: 1},
    {name: 'NO', key: -1},
    {name: 'N/A', key: 0},
  ];
  vigencias = [
    {name: 'Vigente', key: 1},
    {name: 'No vigente', key: 0}
  ];
  desarrollo = [
    {name: 'En Desarrollo', key: 1},
    {name: 'Formalizado y autorizado', key: 2},
    {name: 'Formalizado, autorizado y administrado', key: 3}
  ];
  token = '';


  constructor(private ProyectServices: ProyectoService, private route: ActivatedRoute, private router: Router, breakpointObserver: BreakpointObserver, private EnterprisesService: EnterprisesService) {
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
    this.EnterprisesService.getEnterprises().subscribe(res => {
      console.log(res);
      this.companys = res;
    });
  }


  getPreguntasEmpresa() {
    this.goQuestion = true;
    console.log(this.entrepise);
    this.EnterprisesService.getPreguntas(this.entrepise.id).subscribe(res => {
      console.log(res);
      this.temas = res;
      this.goQuestion = false;
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 4;
    }

  }


  updateValor(question) {
    console.log(question);
    let data = {
      idPregunta: question.id,
      respuesta: question.respuesta
    };
    this.EnterprisesService.updatePregunta(data).subscribe(res => {
    })
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
        this.calculateScoreTema();
        let data = {
          idEmpresa: this.entrepise.id,
          temas: this.temas
        };
        this.EnterprisesService.saveCuestionario(data).subscribe(res => {
          let id = res['id'];
          let obj = {
            id: id,
            value: true,
            score : this.calculateScore()
          };
          this.EnterprisesService.finalizeCuestionarioScore(obj).subscribe(res => {
            Swal(
              'Listo.',
              'El cuestionario se finalizo correctamente',
              'success'
            ).then(() => {
              this.response.emit({value: 1});
            });
          });
        });

      }
    });
  }

  calculateScoreTema() {
    let total = 0;
    let totalTema = 0;
    let scoreFinal = 0;

    for (let tema of this.temas) {
      let totalParcial = 0;
      for (let question of tema['preguntas']) {

        if (question.respuesta1 !== 0) {
          if (question.respuesta1 == 1) {
            totalTema += 1;
            totalParcial += (question.respuesta2 * question.respuesta3);
          } else {
            totalTema += 1;
          }
        }
      }
      tema['score'] = (totalParcial / totalTema);
    }
  }

  calculateScore() {
    let scoreFinal = 0;
    let total = 0;
    for ( let tema of this.temas) {
      total += tema['score'];
    }
    scoreFinal = (total / this.temas.length);
    return scoreFinal;
  }
}
