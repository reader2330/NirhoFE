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
  goQuestion= true;
  companys = [];
  entrepise = {
    id:0
  };
  opts = [
    {name: 'SI', key: 1},
    {name: 'NO', key: -1},
    {name: 'N/A', key: 0},
  ];
  token = '';


  constructor(private ProyectServices: ProyectoService, private route: ActivatedRoute, private router: Router, breakpointObserver: BreakpointObserver,  private EnterprisesService: EnterprisesService) {
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


  getPreguntasEmpresa(){
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
      console.log(res);
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

        this.calculateScore();


        /*this.EnterprisesService.finalizeCuestionario(data).subscribe(res => {
          Swal(
            'Listo.',
            'El cuestionario se finalizo correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        })*/

      }
      });
  }

  calculateScore() {
    let total = 0;
    let cal = 0;
    for (let tema of this.temas) {
      for (let question of tema['preguntas']) {
        console.log(question);
        if (question.respuesta !== 0) {
          total += 1;
          if(question.respuesta === 1) {
            cal += 1;
          }
        }
      }
    }
    console.log(total);
    console.log(cal);
    console.log((cal / total) * 10)
    return (cal / total) * 10;

  }




}
