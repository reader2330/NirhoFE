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
    })
  }


  getPreguntasEmpresa(){
    this.goQuestion = true;
    console.log(this.entrepise);
    this.EnterprisesService.getPreguntasEmpresa(this.entrepise.id).subscribe(res => {
      console.log(res);
      this.temas = res;
      console.log(res[0].temas[0].preguntas);
      this.dataSource = res[0].temas[0].preguntas;
      //this.goQuestion = false;
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
        let data = {
          id: this.temas[0]['id'],
          opt: true
        };
        console.log(data);
        this.EnterprisesService.finalizeCuestionario(data).subscribe(res => {
          Swal(
            'Listo.',
            'El cuestionario se finalizo correctamente',
            'success'
          ).then(() => {
            this.response.emit({value: 1});
          });
        })

      }
      });
  }




}
