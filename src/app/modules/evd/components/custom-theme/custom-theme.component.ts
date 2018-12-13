import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Pregunta} from '../../../clb/models/pregunta';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import Swal from "sweetalert2";
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material';
import {Tema} from '../../models/tema';


@Component({
  selector: 'app-custom-theme',
  templateUrl: './custom-theme.component.html',
  styleUrls: ['./custom-theme.component.scss']
})
export class CustomThemeComponent implements OnInit {

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
    this.ProyectService.getTemas().subscribe(res => {
      console.log(res);
      this.temas = res;
      for (let tema of this.temas) {

      }
      this.temas.push({nombre: ''});
      this.showTemas = true;
      this.save = true;
    });


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
        this.temas.pop();
        let tema = new Tema();
        tema.descripcion = this.newTema['nombre'];
        tema.nombre = this.newTema['nombre'];
        tema.tipo = null;
        tema.idTema = this.temas[this.temas.length - 1]['idTema'] + 1;
        tema['isSelect'] = true;
        this.temas.push(tema);
        this.newTema['nombre'] = '';
        this.temas.push({nombre: '', isSelect: false});
      } else {
        this.snackBar.open('No puedes agregar dos temas con el mismo nombre', 'Claro!' ,{
          duration: 1000
        });

      }
    }

  }

  saveTemas() {
    Swal({
      title: '',
      text: 'Seguro que quieres guardar las competencias',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        let obj = {
          idProyecto: this.proyect.idProyecto,
          lista: []
        };
        for (let tema of this.temas) {
          if (tema.isSelect) {
            delete tema.isSelect;
            obj.lista.push(tema);
          }
        }

        this.ProyectService.savePreguntas(obj).subscribe(res => {
          Swal(
            'Listo.',
            'Los temas se guardaron correctamente',
            'success'
          );
        });
      }
    });

  }




}
