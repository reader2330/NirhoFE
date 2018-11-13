import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-labor-modal-adm',
  templateUrl: './labor-modal-adm.component.html',
  styleUrls: ['./labor-modal-adm.component.scss']
})
export class LaborModalAdmComponent implements OnInit {

  puestos = [];
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
      area: new FormControl('', Validators.required)
    }
  );

  constructor(breakpointObserver: BreakpointObserver, private CatalogsAdmServices: CatalogsAdmService) {
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
  }

  ngOnInit() {
    this.getJob();
    this.getLevelJob();
  }

}
