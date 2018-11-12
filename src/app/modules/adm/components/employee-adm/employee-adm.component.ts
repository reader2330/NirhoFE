import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';

@Component({
  selector: 'app-employee-adm',
  templateUrl: './employee-adm.component.html',
  styleUrls: ['./employee-adm.component.scss']
})
export class EmployeeAdmComponent implements OnInit {

  spins = [];
  employee = {
    id: null,
    nombreCompleto: '',
    nacionalidad: 0,
    fechaNacimiento: '',
    edad: 0,
    rfc: '',
    curp: '',
    nss: '',
    direccion: ''
  };
  nacionalidades = [];
  mobile = false;
  employeeForm = new FormGroup(
    {
      id: new FormControl(null),
      nombreCompleto: new FormControl(null, Validators.required),
      nacionalidad: new FormControl(0, Validators.required),
      fechaNacimiento: new FormControl(null, Validators.required),
      edad: new FormControl(0, Validators.required),
      rfc: new FormControl(null,
        Validators.compose([Validators.required, Validators.maxLength(13)])),
      curp: new FormControl(null,
        Validators.compose([Validators.required, Validators.maxLength(18)])),
      nss: new FormControl(null, Validators.required),
      direccion: new FormControl('', Validators.required)
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

  checkMaxColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMaxTwoColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  getNacionality () {
    this.CatalogsAdmServices.getNacionality().subscribe((res) => {
      if (res) {
        this.nacionalidades = res;
      }
    });
  }

  saveEmployee() {
    console.log(this.employeeForm.value);
    this.employee = this.employeeForm.value;
    sessionStorage.setItem('employee', JSON.stringify(this.employee));
  }

  ngOnInit() {
    this.getNacionality();
  }

}
