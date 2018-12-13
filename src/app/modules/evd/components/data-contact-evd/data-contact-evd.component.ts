import { Component, OnInit } from '@angular/core';

import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';

@Component({
  selector: 'app-data-contact-evd',
  templateUrl: './data-contact-evd.component.html',
  styleUrls: ['./data-contact-evd.component.scss']
})
export class DataContactEvdComponent implements OnInit {

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
  contactForm = new FormGroup(
    {
      id: new FormControl(null),
      telefono: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      puesto: new FormControl(0, Validators.required),
      tipoContacto: new FormControl(0, Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      nombre: new FormControl('', Validators.required),
      celular: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      empresa:  new FormControl(null)
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

  ngOnInit() {
    this.getPuestos();
    this.getTypeContact();
  }

  getPuestos() {
    this.CatalogService.getPuestos().subscribe((res) => {

      if (res) {
       this.puestos = res;
      }
    });

  }
  getTypeContact() {
    this.CatalogService.getTypeContact().subscribe((res) => {

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
    const empresa = JSON.parse(sessionStorage.getItem('entrepise-evd'));
    this.contact = this.contactForm.value;

    sessionStorage.setItem('contact-evd', JSON.stringify(this.contact));
  }

}
