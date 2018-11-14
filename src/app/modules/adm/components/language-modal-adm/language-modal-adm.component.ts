import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import {MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-language-modal-adm',
  templateUrl: './language-modal-adm.component.html',
  styleUrls: ['./language-modal-adm.component.scss']
})
export class LanguageModalAdmComponent implements OnInit {

  lenguajes = [];
  level_lenguages = [];
  hab_lenguages = [];
  mobile = false;
  lenguaje? = {};

  languageForm = new FormGroup(
    {
      idioma: new FormControl(0, [Validators.required]),
      nivel: new FormControl(0, Validators.required),
      habilidades: new FormControl(0, Validators.required)
    }
  );

  constructor(breakpointObserver: BreakpointObserver, private CatalogsAdmServices: CatalogsAdmService, private dialogRef : MatDialogRef<LanguageModalAdmComponent>) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
      } else {
        this.mobile = false;
      }
    });
  }

  getLanguage() {
    this.CatalogsAdmServices.getLanguage().subscribe((res) => {
      if (res) {
        this.lenguajes = res;
      }
    });
  }

  getLevelLanguage() {
    this.CatalogsAdmServices.getLevelLanguage().subscribe((res) => {
      if (res) {
        console.log("ress: ", res)
        this.level_lenguages = res;
      }
    });
  }

  getLevelHabilities() {
    this.CatalogsAdmServices.getLevelHabilities().subscribe((res) => {
      if (res) {
        console.log("rrrr: ", res)
        this.hab_lenguages = res;
      }
    });
  }

  saveLanguage() {
    console.log(this.languageForm.value);
    this.lenguaje = this.languageForm.value;
    sessionStorage.setItem('lenguaje', JSON.stringify(this.lenguaje));
    this.dialogRef.close();
  }

  ngOnInit() {
    this.getLanguage();
    this.getLevelLanguage();
    this.getLevelHabilities();
  }

}
