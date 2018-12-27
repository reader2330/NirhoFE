import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ClienteServiceService} from '../../synchronize/services/cliente-service.service';
import {Usuario} from '../../synchronize/models/usuario';
import {MatDialogRef} from '@angular/material';
import Swal from "sweetalert2";

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
  constructor(private _form: FormBuilder, private ClienteServices: ClienteServiceService, public dialogRef: MatDialogRef<ModalUsuariosComponent>) { }

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
    let user: Usuario;
    user = new Usuario();
    user.id = null;
    user.fullName = this.usuarioForm.value['nombreUsuario'];
    user.username = this.usuarioForm.value['usernameUsuario'];
    user.password = this.usuarioForm.value['passwordUsuario'];
    user.email = this.usuarioForm.value['emailUsuario'];
    user.rol =  this.usuarioForm.value['rolUsuario'];
    Swal({
        title: '',
        text: 'Seguro que quieres guardar al usuario',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si guardar',
        cancelButtonText: 'No, seguir editando'
      }).then((result) => {
        if (result.value) {
          this.ClienteServices.saveUsuario(user).subscribe(res => {
            Swal(
              'Listo.',
              'Se guardo correctamente',
              'success'
            ).then(() => {
              this.dialogRef.close();
            });
            });
          }
        });
      }

}
