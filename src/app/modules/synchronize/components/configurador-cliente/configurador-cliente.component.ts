import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material';
import {ModalUsuariosComponent} from '../../../modal/modal-usuarios/modal-usuarios.component';
import {ClienteServiceService} from '../../services/cliente-service.service';

@Component({
  selector: 'app-configurador-cliente',
  templateUrl: './configurador-cliente.component.html',
  styleUrls: ['./configurador-cliente.component.scss']
})
export class ConfiguradorClienteComponent implements OnInit {
  clienteForm: FormGroup;
  usuarios = [];
  constructor(private _form: FormBuilder, private modal: MatDialog, private ClienteServices: ClienteServiceService) {

  }

  ngOnInit() {
    this.clienteForm = this._form.group({
      nombreCliente: ['', Validators.required],
      rfcCliente: ['', Validators.required],
      correoCliente: ['', [Validators.required, Validators.email]],
      telefonoCliente: ['', Validators.required]
    });
  }

  ModalUsuario() {
   this.modal.open(ModalUsuariosComponent, {
   });
  }

  saveCliente() {
    console.log(this.clienteForm.value);
    this.ClienteServices.saveClient(this.clienteForm.value).subscribe(res => {
      console.log(res);
    });
  }

}
