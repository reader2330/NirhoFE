import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-encuesta-modal-evd',
  templateUrl: './encuesta-modal-evd.component.html',
  styleUrls: ['./encuesta-modal-evd.component.scss']
})
export class EncuestaModalEvdComponent implements OnInit {

  quiz: {};

  encuestaForm = new FormGroup(
    {
      factor: new FormControl('', [Validators.required]),
      descricpion: new FormControl('', Validators.required),
      tipo: new FormControl(null, Validators.required)
    }
  );

  constructor(private dialogRef: MatDialogRef<EncuestaModalEvdComponent>) {}

  ngOnInit() {
  }

  saveQuiz() {
    console.log(this.encuestaForm.value);
    this.quiz = this.encuestaForm.value;
    sessionStorage.setItem('quiz_detail', JSON.stringify(this.quiz));
    this.dialogRef.close();
  }

}
