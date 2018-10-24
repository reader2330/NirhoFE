import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';

@Component({
  selector: 'app-data-company',
  templateUrl: './data-company.component.html',
  styleUrls: ['./data-company.component.scss']
})
export class DataCompanyComponent implements OnInit {
  spins = [
    {
    id: 1,
    description: 'Hola'
    },
    {
      id: 2,
      description: 'Adios'
    },
    {
      id: 3,
      description: 'Muestra'
    }
  ];
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

  ngOnInit() {
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
      console.log(res);
      if (res.data) {
        this.countries = res.data;
      }
    });
  }
  getGiros() {
    this.CatalogService.getCountries().subscribe((res) => {
      console.log(res);
      if (res.data) {
        this.spins   = res.data;
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

}
