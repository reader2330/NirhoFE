import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {EnterprisesService} from '../../../irh/services/enterprises.service';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';

@Component({
  selector: 'app-enterprise-evd',
  templateUrl: './enterprise-evd.component.html',
  styleUrls: ['./enterprise-evd.component.scss']
})
export class EnterpriseEvdComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  mgsInit = '';
  countries = [];
  spins = [];
  empresa = [];
  nacionalidades = [];


  enterpriseForm = new FormGroup(
    {
      empresa: new FormControl('', Validators.required),
      pais: new FormControl(0, Validators.required),
      rfc: new FormControl('', Validators.required),
      giro: new FormControl('', Validators.required),
      direccion: new FormControl('', Validators.required),
    }
  );

  constructor( breakpointObserver: BreakpointObserver, private ProyectoEvdServices: ProyectoEvdService, private EntrepiseService: EnterprisesService) {
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
      console.log(this.mobile);
    });
  }

  ngOnInit() {
    this.getNacionality();
    this.getGiros();
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
    this.ProyectoEvdServices.getNacionality().subscribe((res) => {
      if (res) {
        console.log(res);
        this.nacionalidades = res;
      }
    });
  }

  getGiros() {
    this.ProyectoEvdServices.getGiros().subscribe((res) => {
      if (res) {
        console.log(res);
        this.spins = res;
      }
    });
  }

  guardarEmpresa() {
    console.log(this.enterpriseForm.value);
    this.empresa = this.enterpriseForm.value;
    sessionStorage.setItem('enterprise', JSON.stringify(this.empresa));
  }

}
