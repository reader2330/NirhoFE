import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {MatChipInputEvent} from '@angular/material';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import {COMMA, ENTER} from '@angular/cdk/keycodes';


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
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  certificaciones = [
  ];
  oficios = [];
  cursos = [];

  add(event: MatChipInputEvent, module): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim() && module === 1) {
      this.certificaciones.push(value.trim());
    }
    if ((value || '').trim() && module === 2) {
      this.cursos.push( value.trim());
    }
    if ((value || '').trim() && module === 3) {
      this.oficios.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(certificacion ): void {
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
    escolaridadCertificaciones: [],
    escolaridadCursos: [],
    escolaridadOficios: [],
    titulo: false
  };
  escolaridades = [

      {
        id:1,
        descripcionCatalogo:"Primaria Trunca"
      },
      {
        id:2,
        descripcionCatalogo:"Primaria Completa"
      },
      {
        id:3,
        descripcionCatalogo:"Secundaria Trunca"
      },
      {
        id:4,
        descripcionCatalogo:"Secundaria Completa"
      },
      {
        id:5,
        descripcionCatalogo:"Carrera Técnica Completa"
      },
      {
        id:6,
        descripcionCatalogo:"Carrera Técnica Incompleta"
      },
      {
        id:7,
        descripcionCatalogo:"Bachillerato Completo"
      },
      {
        id:8,
        descripcionCatalogo:"Bachillerato Incompleto"
      },
      {
        id:9,
        descripcionCatalogo:"Universidad Completa"
      },
      {
        id:10,
        descripcionCatalogo:"Universidad Incompleta"
      },
      {
        id:11,
        descripcionCatalogo:"Maestría Completa"
      },
      {
        id:12,
        descripcionCatalogo:"Maestría Incompleta"
      },
      {
        id:13,
        descripcionCatalogo:"Doctorado Completo"
      },
      {
        id:14,
        descripcionCatalogo:"Doctorado Incompleto"
      },
      {
        id:15,
        descripcionCatalogo:"Universitario Técnico Completo"
      },
      {
        id:16,
        descripcionCatalogo:"Universitario Técnico Incompleto"
      },
      {
        id:17,
        descripcionCatalogo:"Otro"
      }

  ];
  mobile = false;
  scholarshipForm = new FormGroup(
    {
      escolaridad: new FormControl('', [Validators.required]),
      escolaridadCarrera: new FormControl('', Validators.required),
      escolaridadEspecialidad: new FormControl('', Validators.required),
      escolaridadCapacidades: new FormControl('', [Validators.required]),
      escolaridadCertificaciones: new FormArray([], Validators.required),
      escolaridadCursos: new FormArray([], [Validators.required]),
      escolaridadOficios:  new FormArray([], Validators.required),
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

    this.scholarship =  this.scholarshipForm.value;
    this.scholarship.escolaridadCertificaciones = this.certificaciones;
    this.scholarship.escolaridadCursos = this.cursos;
    this.scholarship.escolaridadOficios = this.oficios;
    console.log(this.scholarship);
    sessionStorage.setItem('scholarship', JSON.stringify(this.scholarship));
  }

  ngOnInit() {
    //this.getScholarship();
  }

}
