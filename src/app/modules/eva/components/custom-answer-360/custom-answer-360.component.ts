import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import Swal from "sweetalert2";
import {Proyecto360Service} from '../../services/proyecto360.service';
import {Answer} from '../../../evd/models/answer';

@Component({
  selector: 'app-custom-answer-360',
  templateUrl: './custom-answer-360.component.html',
  styleUrls: ['./custom-answer-360.component.scss']
})
export class CustomAnswer360Component implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  selectProyect = false;
  proyects = [];
  proyect = {
    idProyecto: undefined
  };
  tema = {
    idTema: 0
  };
  temas = [];
  load = false;
  save = false;
  select = [];
  newTema = {};
  showOptions = false;
  answers = [];
  answersSelect = [];
  opts = ['BR', 'MR', 'R', 'RS', 'E'];




  constructor( private ProyectService: Proyecto360Service, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getProyects();


  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {
      this.proyects = res;
    });
  }
  /*getAnswerByTheme() {
    this.ProyectService.getThemaByProyect(this.proyect.idProyecto).subscribe(res => {
      console.log(res);
      this.temas = res;
      this.showOptions = true;
    });


  }*/

  addAnswer() {
    let add = true;
    if (this.newTema['enunciado'].trim() !== '') {
      for (let tema of this.answers) {
        if (tema.enunciado.toUpperCase() === this.newTema['enunciado'].trim().toUpperCase()) {
          add = false;
        }
      }
      if (add) {
        let index;
        this.answers.pop();
        let answer = new Answer();
        answer.enunciado = this.newTema['enunciado'];
        answer.tipo = this.newTema['tipo'];
        if (this.answers[this.answers.length - 1] && this.answers[this.answers.length - 1]['idOpcion']) {
          answer.idOpcion = this.answers[this.answers.length - 1]['idOpcion'] + 1;
          answer['isSelect'] = true;
          for (let ans of this.answers) {
            if (ans.tipo === this.newTema['tipo']) {
              index = this.answers.indexOf(ans);
              answer['idTema'] = ans.idTema;
            }
          }
          if (index !== undefined) {
            console.log('quito');
            this.answers.splice(index, 1, answer);
          } else {
            console.log('pongo');
            answer.idTema = this.tema;
            this.answers.push(answer);

          }
          this.newTema['enunciado'] = '';
          this.newTema['tipo'] = '';
          this.answers.push({enunciado: '', isSelect: false});
        }
        else {
          answer.idOpcion = this.generateID(this.tema['idTema']);
          answer['isSelect'] = true;
          for (let ans of this.answers) {
            if (ans.tipo === this.newTema['tipo']) {
              index = this.answers.indexOf(ans);
              answer['idTema'] = ans.idTema;
            }
          }
          console.log(index);
          if (index !== undefined) {
            console.log('quito');
            this.answers.splice(index, 1, answer);
          } else {
            console.log('pongo');
            answer.idTema = this.tema;
            this.answers.push(answer);
          }
          this.newTema['enunciado'] = '';
          this.newTema['tipo'] = '';
          this.answers.push({enunciado: '', isSelect: false});
        }

      } else {
        this.snackBar.open('No puedes agregar dos respuestas con el mismo enunciado', 'Claro!' , {
          duration: 1000
        });

      }
    }

  }

  countList() {
    let count = 0;
    for (let ans of this.answers) {
      if (ans.isSelect) {
        count++;
      }
    }
    if (count === 5) {
      return false;
    }
    return true;
  }

  saveAnswer() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las respuestas',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        for (let ans of this.answers) {
          if (ans.isSelect) {
            delete ans.isSelect;
            this.answersSelect.push(ans);
          }
        }
        let obj = {
          idProyecto: this.proyect.idProyecto,
          lista: this.answersSelect
        };
        this.ProyectService.saveAnswer(obj).subscribe(res => {
          Swal(
            'Listo.',
            'Las respuestas se guardaron correctamente',
            'success'
          );
          let index;
          for (let tem of this.temas) {
            if (tem.idTema === this.tema.idTema ){
              index = this.temas.indexOf(tem);
            }
          }
          this.temas.splice(index, 1);
          if(this.temas.length === 0){
            this.response.emit({value:1});
          }
          this.answersSelect = [];
        });
      }
    });
  }

  showAnswer() {
    this.ProyectService.getAnswerbyTema(this.tema.idTema).subscribe(res => {
      console.log(res);
      this.showOptions = true;
      this.answers = res;
      for (let ans of this.answers) {
        ans['isSelect'] = false;
      }
      this.answers.push({enunciado: '', isSelect: false});
    });
  }

  showSendQuewstion() {

  }

  sendQuestion() {

  }
  getAnswerByTheme() {
    this.ProyectService.getThemaByProyect(this.proyect.idProyecto).subscribe(res => {
      console.log(res);
      this.temas = res;
      this.showOptions = true;
    });
  }

  generateID(id) {
    let number = id + '001';
    console.log(number);
    return parseInt(number);

  }



}
