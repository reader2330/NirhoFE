import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';

@Component({
  selector: 'app-contact-adm',
  templateUrl: './contact-adm.component.html',
  styleUrls: ['./contact-adm.component.scss']
})
export class ContactAdmComponent implements OnInit {

  contacto = {
    celular: 0,
    email: '',
    nombre: '',
    puesto: '',
    telefono: 0,
    tipoContacto: 0
  };
  tipoContacto = [

      {
        id:1,
        descripcionCatalogo: "Padre"
      },
      {
        id:2,
        descripcionCatalogo: "Madre"
      },
      {
        id:3,
        descripcionCatalogo:"Hermano / Hermana"
      },
      {
        id:4,
        descripcionCatalogo:"Esposo/Esposa"
      },
      {
        id:5,
        descripcionCatalogo:"Hijo / Hija"
      },
      {
        id:6,
        descripcionCatalogo:"Abuelo / Abuela"
      },
      {
        id:7,
        descripcionCatalogo:"Nieto / Nieta"
      },
      {
        id:8,
        descripcionCatalogo:"Tío / Tía"
      },
      {
        id:9,
        descripcionCatalogo:"Amigo / Amiga"
      },
      {
        id:10,
        descripcionCatalogo:"Concubina / Concubino"
      },
      {
        id:11,
        descripcionCatalogo:"Otro"
      }

  ];
  puestos = [];
  mobile = false;
  contactForm = new FormGroup(
    {
      telefono: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      beneficiario: new FormControl('', Validators.required),
      tipoContacto: new FormControl(0, Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      nombre: new FormControl('', Validators.required),
      celular: new FormControl('', [Validators.required, Validators.maxLength(10)])
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

  getTypeContac() {
    this.CatalogsAdmServices.getTypeContac().subscribe((res) => {
      if (res) {
        this.tipoContacto = res;
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

  saveContact() {
    console.log(this.contactForm.value);
    this.contacto = this.contactForm.value;
    sessionStorage.setItem('contacto', JSON.stringify(this.contacto));
  }

  ngOnInit() {
    //this.getTypeContac();
    this.getJob();
    this.getTypeContac();
  }

}
