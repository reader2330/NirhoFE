import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../clb/services/catalogs.service';

@Component({
  selector: 'app-informacion-form',
  templateUrl: './informacion-form.component.html',
  styleUrls: ['./informacion-form.component.scss']
})
export class InformacionFormComponent implements OnInit {
  InformacionForm: FormGroup;
  ContactoForm: FormGroup;
  VacanteForm: FormGroup;
  paises = [];
  giros = [];
  contacts = [];
  newActiviad = {
    nombreActividad:null,
    descripcionActividad: null,
    nivel:null
  };
  displayActividades = ['nombre', 'descripcion', 'nivel', 'detail1'];
  constructor(private _form: FormBuilder, private CatalogServices: CatalogsService) { }

  ngOnInit() {
    this.InformacionForm = this._form.group({
      rfc: [null, [Validators.required, Validators.maxLength(13)]],
      empresa: ['', Validators.required],
      pais: [null, Validators.required],
      giro: [null, Validators.required],
      direccion: [null, Validators.required]
    });
    this.ContactoForm = this._form.group({
      nombreContacto: ['', Validators.required],
      puestoContacto: ['', Validators.required],
      tipoContacto: [null, Validators.required],
      emailContacto: ['', Validators.required],
      telefonoContacto: ['', Validators.required],
      celularContacto: ['', Validators.required]
    });
    this.VacanteForm = this._form.group({
      nombreVacante: ['', Validators.required],
      giro: [null, Validators.required],
      motivo: [null, Validators.required],
      numeroVacantes: [null, Validators.required],
      puestoVacante: this._form.group({
        nombrePuesto: ['', Validators.required],
        puestoReporta: ['', Validators.required],
        yearsExperencia: ['', Validators.required],
        puestoCargo: ['', Validators.required]
      })
    });
    this.getPaises();
    this.getGiros();
    this.getContactos();

  }

  getPaises() {
    this.CatalogServices.getCountries().subscribe(res => {
      this.paises = res;
    });
  }
  getGiros() {
    this.CatalogServices.getGiros().subscribe(res => {
      this.giros = res;
    });
  }
  getContactos() {
    this.CatalogServices.getTypeContact().subscribe(res => {
      this.contacts = res;
    });
  }

  mostrarForm(form: any){
    console.log(form['value']);
  }

}
