import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-labor-modal-adm',
  templateUrl: './labor-modal-adm.component.html',
  styleUrls: ['./labor-modal-adm.component.scss']
})
export class LaborModalAdmComponent implements OnInit {

  puestos = [
    {
      id:1,
      descripcionCatalogo:"Becario"
    },
    {
      id:2,
      descripcionCatalogo:"Analista"
    },
    {
      id:3,
      descripcionCatalogo:"Consultor junior"
    },
    {
      id:4,
      descripcionCatalogo:"Consultor semisenior"
    },
    {
      id:5,
      descripcionCatalogo:"Consultor senior"
    },
    {
      id:6,
      descripcionCatalogo:"Gerente de operaciones"
    },
    {
      id:7,
      descripcionCatalogo:"Analista de ventas"
    },
    {
      id:8,
      descripcionCatalogo:"Consultor ventas junior"
    },
    {
      id:9,
      descripcionCatalogo:"Consultor ventas semisenior"
    },
    {
      id:10,
      descripcionCatalogo:"Consultor ventas senior"
    },
    {
      id:11,
      descripcionCatalogo:"Gerente de ventas"
    }
  ];
  nivelesLaborales = [];
  mobile = false;
  job_detail = {};
  laborForm = new FormGroup(
    {
      puesto: new FormControl(0, [Validators.required]),
      nivelLaboral: new FormControl(0, Validators.required),
      fechaInicio: new FormControl('', Validators.required),
      fechaTermino: new FormControl('', Validators.required),
      antiguedad: new FormControl(0, Validators.required),
      localidad: new FormControl('', Validators.required),
      area: new FormControl('', Validators.required),
      sueldo: new FormControl('', Validators.required)
    }
  );
  tipoContrato = [
    {
      id:1,
      descripcionCatalogo:"De ley"
    },
    {
      id:2,
      descripcionCatalogo:"Superiores de ley"
    },
    {
      id:3,
      descripcionCatalogo:"Honorarios"
    },
    {
      id:4,
      descripcionCatalogo:"Salarios asimilados"
    },
    {
      id:5,
      descripcionCatalogo:"Comisionista"
    },
    {
      id:6,
      descripcionCatalogo:"Becario"
    },
    {
      id:7,
      descripcionCatalogo: "Servicio social"
    },
    {
      id:8,
      descripcionCatalogo:"Otro"
    }
    ];

  constructor(breakpointObserver: BreakpointObserver, private CatalogsAdmServices: CatalogsAdmService , private dialogRef : MatDialogRef<LaborModalAdmComponent>) {
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

  getJob() {
    this.CatalogsAdmServices.getJob().subscribe((res) => {
      if (res) {
        this.puestos = res;
      }
    });
  }

  getLevelJob() {
    this.CatalogsAdmServices.getLevelJob().subscribe((res) => {
      if (res) {
        this.nivelesLaborales = res;
      }
    });
  }

  saveJob() {
    console.log(this.laborForm.value);
    this.job_detail = this.laborForm.value;
    sessionStorage.setItem('job_detail', JSON.stringify(this.job_detail));
    this.dialogRef.close();
  }

  ngOnInit() {
    //this.getJob();
    //this.getLevelJob();
  }

}
