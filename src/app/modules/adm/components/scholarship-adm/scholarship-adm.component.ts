import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {MatChipInputEvent} from '@angular/material';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';


export interface Certificacion {
  certificaciones: {
    certificacion: string
  };
}

@Component({
  selector: 'app-scholarship-adm',
  templateUrl: './scholarship-adm.component.html',
  styleUrls: ['./scholarship-adm.component.scss']
})
export class ScholarshipAdmComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  certificaciones: Certificacion[] = [
  ];

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.certificaciones.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(certificacion: Certific): void {
    const index = this.certificaciones.indexOf(certificacion);

    if (index >= 0) {
      this.certificaciones.splice(index, 1);
    }
  }
  scholarship = {
    escolaridad: 0,
    escolaridadCarrera: '',
    escolaridadEspecialidad: '',
    escolaridadCapacidades: '',
    escolaridadCertificaciones: '',
    escolaridadCursos: '',
    escolaridadOficios: '',
    titulo: false
  };
  escolaridades = [];
  mobile = false;
  scholarshipForm = new FormGroup(
    {
      escolaridad: new FormControl('', [Validators.required]),
      escolaridadCarrera: new FormControl('', Validators.required),
      escolaridadEspecialidad: new FormControl('', Validators.required),
      escolaridadCapacidades: new FormControl('', [Validators.required]),
      escolaridadCertificaciones: new FormControl('', Validators.required),
      escolaridadCursos: new FormControl('', [Validators.required]),
      escolaridadOficios:  new FormControl(''),
      titulo:  new FormControl(false)
    }
  );
  constructor(breakpointObserver: BreakpointObserver, private CatalogsAdmServices: CatalogsAdmService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
        this.checkMobileCols();
      } else {
        this.mobile = false;
        this.checkMobileCols();
      }
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }
  }

  getScholarship() {
    this.CatalogsAdmServices.getScholarship().subscribe((res) => {
      if (res) {
        this.escolaridades = res;
      }
    });
  }

  saveScholarship() {
    console.log(this.scholarshipForm.value);
    this.scholarship = this.scholarshipForm.value;
    sessionStorage.setItem('scholarship', JSON.stringify(this.scholarship));
  }

  ngOnInit() {
    this.getScholarship();
  }

}
