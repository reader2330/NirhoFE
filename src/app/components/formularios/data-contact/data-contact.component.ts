import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';

@Component({
  selector: 'app-data-contact',
  templateUrl: './data-contact.component.html',
  styleUrls: ['./data-contact.component.scss']
})
export class DataContactComponent implements OnInit {

  contact = {
    id: 0,
    celular: 1234,
    email: '',
    nombre: '',
    puesto: '',
    telefono: 1234,
    tipoContacto: 1,
    empresa: {}
  };
  puestos = [];
  typeContact = [];
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

  getPuestos() {
    this.CatalogService.getPuestos().subscribe((res) => {
      console.log(res);
      if (res) {
       this.puestos = res;
      }
    });

  }
  getTypeContact() {
    this.CatalogService.getPuestos().subscribe((res) => {
      console.log(res);
      if (res) {
        this.typeContact = res;
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

  saveContact() {
    const empresa = JSON.parse(sessionStorage.getItem('company'));
    this.contact.empresa = empresa;
    sessionStorage.setItem('contact', JSON.stringify(this.contact));
  }

}
