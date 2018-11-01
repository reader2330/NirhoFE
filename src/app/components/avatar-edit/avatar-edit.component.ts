import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2'

export interface Avatar {
  value: string;
  url: string;
}

@Component({
  selector: 'app-avatar-edit',
  templateUrl: './avatar-edit.component.html',
  styleUrls: ['./avatar-edit.component.scss']
})

export class AvatarEditComponent implements OnInit {

  avatar = {
    url: String
  }

  selectedValue : String;

  avatares: Avatar[] = [
    {value: 'avatar1', url: 'logo.png'},
    {value: 'avatar2', url: 'avatar.png'},
    {value: 'avatar3', url: 'x1.jpg'}
  ];

  avatarForm = new FormGroup({
    url_avatar: new FormControl('')
  });

  saveAvatar () {
    this.avatar = this.avatarForm.value;
    console.log("this: ", this.avatar);
    Swal({
      title: '',
      text: 'Guardado exitoso',
      type: 'success',
      showCancelButton: false,
      confirmButtonText: 'Guardar',
      cancelButtonText: 'Cancelar'
    })
  }

  constructor() { }

  ngOnInit() {
  }

}
