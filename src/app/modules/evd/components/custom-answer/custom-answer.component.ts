import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-custom-answer',
  templateUrl: './custom-answer.component.html',
  styleUrls: ['./custom-answer.component.scss']
})
export class CustomAnswerComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  selectProyect = false;
  proyects = [];
  proyect = {
    idProyecto: undefined
  };
  temas = [];
  load = false;
  save = false;
  select = [];
  showTemas = false;
  newTema = {};




  constructor( private ProyectService: ProyectoEvdService, public snackBar: MatSnackBar) { }

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
  getTemas() {
    this.temas.push({nombre: ''});
    this.showTemas = true;

  }

  addTema() {
    let add = true;
    if (this.newTema['nombre'].trim() !== '') {
      for (let tema of this.temas) {
        if (tema.nombre.toUpperCase() === this.newTema['nombre'].trim().toUpperCase()) {
          add = false;
        }
      }
      if (add) {
        this.temas.push({nombre: this.newTema['nombre'].trim(), isSelect: true});
        this.newTema['nombre'] = '';
      } else {
        this.snackBar.open('No puedes agregar dos temas con el mismo nombre', 'Claro!' ,{
          duration: 1000
        });

      }
    }

  }

  saveTemas() {

  }



}
