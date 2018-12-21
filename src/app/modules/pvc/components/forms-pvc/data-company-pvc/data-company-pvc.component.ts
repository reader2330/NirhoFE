import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../../clb/services/catalogs.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-data-company-pvc',
  templateUrl: './data-company-pvc.component.html',
  styleUrls: ['./data-company-pvc.component.scss']
})
export class DataCompanyPvcComponent implements OnInit {

  spins = [];
  company = {
    id: null,
    direccion: '',
    empresa: '',
    giro: 0,
    pais: 0,
    rfc : '',
  };
  countries = [];
  mobile = false;
  companyForm = new FormGroup(
    {
      id: new FormControl(null),
      anioInicioOperaciones: new FormControl(0),
      facturacionAnual: new FormControl(0),
      productoServicioEstrella: new FormControl(null),
      principalesProductosServicios: new FormControl(null),
      noEmpleadosAdministrativo: new FormControl(0),
      noEmpleadosOperativo: new FormControl(0),
      tipoContratacionEmpleados: new FormControl(null),
      direccion: new FormControl('', Validators.required),
      giro: new FormControl(0, Validators.required),
      pais: new FormControl(0, Validators.required),
      rfc: new FormControl('', Validators.required),
      empresa: new FormControl('', Validators.required)
    }
    );
  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService) {
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

  get pais() {
    return this.companyForm.get('pais');
  }


  ngOnInit() {
    this.getCountries();
    this.getGiros();

  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }
  getCountries() {
    this.CatalogService.getCountries().subscribe((res) => {
      if (res) {
        this.countries = res;
      }
    });
  }
  getGiros() {
    this.CatalogService.getGiros().subscribe((res) => {
      if (res) {
        console.log(res);
        this.spins = res;
      }
    });
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

  saveCompany() {
    console.log(this.companyForm.value);
    this.company = this.companyForm.value;
    sessionStorage.setItem('company', JSON.stringify(this.company));
  }
  cancelCompany() {}
}
