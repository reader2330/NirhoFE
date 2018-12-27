import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material';
import {ModalUsuariosComponent} from '../../../modal/modal-usuarios/modal-usuarios.component';
import {ClienteServiceService} from '../../services/cliente-service.service';
import Swal from "sweetalert2";
import {Cliente} from '../../models/cliente';
import {Router} from '@angular/router';

@Component({
  selector: 'app-configurador-cliente',
  templateUrl: './configurador-cliente.component.html',
  styleUrls: ['./configurador-cliente.component.scss']
})
export class ConfiguradorClienteComponent implements OnInit {
  clienteForm: FormGroup;
  usuarios = [];
  modulesOPT = [];
  modules = [
    {
      id: 1,
      nombre: 'Clima laboral',
      checkModulo: false
    },
    {
      id: 2,
      nombre: 'Ingeniería de Recursos Humanos',
      checkModulo: false
    },
    {
      id: 3,
      nombre: 'Nómina',
      checkModulo: false
    },
    {
      id: 4,
      nombre: 'Evaluación del Desempeño',
      checkModulo: false
    },
    {
      id: 5,
      nombre: 'Evaluación del Desempeño 360',
      checkModulo: false
    },
    {
      id: 6,
      nombre: 'Administración por Objetivos',
      checkModulo: false
    },
    {
      id: 7,
      nombre: 'Plan de Vida y  Carrera',
      checkModulo: false
    },
    {
      id: 8,
      nombre: 'Reclutamiento y Selección de Personal',
      checkModulo: false
    }
    ];
  constructor(private _form: FormBuilder, private modal: MatDialog, private ClienteServices: ClienteServiceService, private route: Router) {

  }

  ngOnInit() {
    this.clienteForm = this._form.group({
      nombreCliente: ['', Validators.required],
      rfcCliente: ['', Validators.required],
      correoCliente: ['', [Validators.required, Validators.email]],
      telefonoCliente: ['', Validators.required]
    });
    this.getUsarios();
  }

  ModalUsuario() {
   let modal = this.modal.open(ModalUsuariosComponent, {
   });
   modal.afterClosed().subscribe(res => {

     this.getUsarios();
   });
  }
  getUsarios() {
    this.ClienteServices.getUsers().subscribe(res => {
      console.log(res);
      this.usuarios = res;
      console.log(this.usuarios);
    });
  }


  saveCliente() {

    Swal({
      title: '',
      text: 'Seguro que quieres guardar al usuario',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        let cliente = new Cliente();
        cliente.id = null;
        cliente.nombre = this.clienteForm.value['nombreCliente'];
        cliente.correo = this.clienteForm.value['correoCliente'];
        cliente.telefono = this.clienteForm.value['telefonoCliente'];
        this.ClienteServices.saveClient(cliente).subscribe(res => {
          Swal(
            'Listo.',
            'Se guardo correctamente',
            'success'
          );
        });
      }
    });
  }
  showRol(id) {
    switch (id) {
      case 1:
        return 'Consultor ventas';
      case 2:
        return 'Gerente Nirho';
      case 3:
        return 'Consultor Nirho';
    }
  }
  addModule(module) {
    module.checkModulo = !module.checkModulo;
    if (module.checkModulo) {
      this.modulesOPT.push(module);
    } else {
      this.modulesOPT.map((item, i) => {
        if (item.id === module.id) {
          this.modulesOPT.splice(i, 1);
          return;
        }
      });
    }
  }
  saveModules() {

    Swal({
      title: '',
      text: 'Seguro que quieres guardar al usuario',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ClienteServices.saveModules(this.modulesOPT).subscribe(res => {
          Swal(
            'Listo.',
            'Se guardo correctamente',
            'success'
          );
        });
      }
      });
  }

  IrInicio() {
    this.route.navigate(['SYNC']);
  }
  cerraSesion() {
    sessionStorage.clear();
    localStorage.clear();
    this.route.navigate(['']);
  }

}
