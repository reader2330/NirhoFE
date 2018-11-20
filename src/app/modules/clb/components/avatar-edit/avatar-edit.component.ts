import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import {environment} from '../../../../../environments/environment';
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
  };

  selectedValue : String;

  avatares: Avatar[] = [
    {value: 'avatar1', url: environment.urlNG + 'assets/logo.png'},
    {value: 'avatar2', url: environment.urlNG + 'assets/avatar.png'},
    {value: 'avatar3', url: environment.urlNG + 'assets/X2.svg'}
  ];

  avatarForm = new FormGroup({
    url_avatar: new FormControl('')
  });

  saveAvatar () {

    this.avatar = this.avatarForm.value;
    Swal({
      title: '',
      text: 'Seguro que quieres ese avatar',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        console.log(this.selectedValue);
        this.LoginService.updateAvatar(this.selectedValue).subscribe((res) => {
          console.log(res);
        });

        Swal({
          title: '',
          text: 'Guardado exitoso',
          type: 'success',
          showCancelButton: false,
          confirmButtonText: 'Guardar',
          cancelButtonText: 'Cancelar'
        }).then((res) => {
          this.router.navigate(['']);
        });

      }
    });
  }

  constructor(private LoginService: LoginService, private router:Router) { }

  ngOnInit() {
    this.LoginService.getUser().subscribe((res) => {
      console.log(res);
    });
  }

}
