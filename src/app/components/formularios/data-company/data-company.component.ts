import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-data-company',
  templateUrl: './data-company.component.html',
  styleUrls: ['./data-company.component.scss']
})
export class DataCompanyComponent implements OnInit {

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
      direccion: new FormControl('', Validators.required),
      giro: new FormControl(0, Validators.required),
      pais: new FormControl(0, Validators.required),
      rfc: new FormControl('', Validators.required),
      nombre: new FormControl('', Validators.required)
    }
    );
  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private formBuilder:FormBuilder) {
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
    this.CatalogService.getCountries().subscribe((res) =>{
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

    this.company = this.companyForm.value;
    console.log(this.company);
    sessionStorage.setItem('company', JSON.stringify(this.company));
  }
  cancelCompany(){}


}
