import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ClienteServiceService} from '../../synchronize/services/cliente-service.service';

@Component({
  selector: 'app-modal-usuarios',
  templateUrl: './modal-usuarios.component.html',
  styleUrls: ['./modal-usuarios.component.scss']
})
export class ModalUsuariosComponent implements OnInit {
  usuarioForm: FormGroup;
  roles = [
    {
      key: 1,
      name: 'Consultor ventas'
    },
    {
      key: 2,
      name: 'Gerente nirho'
    },
    {
      key: 3,
      name: 'Consultor nirho',
    }
    ];
  constructor(private _form: FormBuilder, private ClienteServices: ClienteServiceService) { }

  ngOnInit() {
    this.usuarioForm = this._form.group({
      nombreUsuario: ['', Validators.required],
      usernameUsuario: ['', Validators.required],
      passwordUsuario: ['', [Validators.required, Validators.minLength(8)]],
      emailUsuario: ['', [Validators.required, Validators.email]],
      rolUsuario: [null, Validators.required]
    });
  }

  addUsuario() {
    console.log(this.usuarioForm.value);
    this.ClienteServices.saveUsuario().subscribe(res => {

    });
  }

}
