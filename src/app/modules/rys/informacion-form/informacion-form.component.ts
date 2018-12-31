import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../clb/services/catalogs.service';
import {MatTableDataSource} from '@angular/material';
import {ActividadSolicitante} from '../../../screensOut/models/actividad-solicitante';
import {ReclutamientoService} from '../services/reclutamiento.service';


@Component({
  selector: 'app-informacion-form',
  templateUrl: './informacion-form.component.html',
  styleUrls: ['./informacion-form.component.scss']
})
export class InformacionFormComponent implements OnInit {
  InformacionForm: FormGroup;
  ContactoForm: FormGroup;
  VacanteForm: FormGroup;
  CaracteristicasForm: FormGroup;
  showTable = false;
  paises = [];
  giros = [];
  dataSource = new MatTableDataSource<ActividadSolicitante>();
  contacts = [];
  newActividad = {
    nombreActividad: null,
    descripcionActividad: null,
    nivel: null
  };
  actividades: ActividadSolicitante[] = [];
  displayActividades = ['nombreActividad', 'descripcionActividad', 'nivel', 'detail1'];
  constructor(private _form: FormBuilder, private CatalogServices: CatalogsService, private Recultamiento: ReclutamientoService) { }

  ngOnInit() {
    this.InformacionForm = this._form.group({
      id: [null],
      rfc: [null, [Validators.required, Validators.maxLength(13)]],
      nombre: ['', Validators.required],
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
    this.CaracteristicasForm = this._form.group({
      genero: [null, Validators.required],
      estadoCivil: [null, Validators.required],
      DisponibilidadViajar: [null, Validators.required],
      CambioResidencia: [null, Validators.required],
      NecesidadesEspeciales: [null, Validators.required],
      minimaEdad: [null, Validators.required],
      maximaEdad: [null, Validators.required],
      caracteristicasAdicionales: [null, Validators.required]
    });
    this.getPaises();
    this.getGiros();
    this.getContactos();

  }

  addActividad() {
    let actividad = new ActividadSolicitante();
    actividad.nombreActividad = this.newActividad.nombreActividad;
    actividad.descripcionActividad = this.newActividad.descripcionActividad;
    actividad.nivel = this.newActividad.nivel;
    this.newActividad.descripcionActividad = null;
    this.newActividad.nombreActividad = null;
    this.newActividad.nivel = null;
    this.actividades.push(actividad);
    this.dataSource.data = this.actividades;
    this.showTable = true;
  }
  removeActividad(elt) {
    this.actividades.map((item, i ) => {
      if (item.nombreActividad === elt.nombreActividad) {
        this.actividades.splice(i, 1);
      }
    });
    this.dataSource.data = this.actividades;
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
  searchByRFC() {
    if (this.InformacionForm.value['rfc']) {
      const rfc = this.InformacionForm.value['rfc'];
      this.Recultamiento.getSolicitanteByRFC(rfc).subscribe(res => {
        console.log(res);
      })
    }
  }

  mostrarForm(form: any) {
    console.log(form['value']);
  }
  guardarForm (form , step) {

    switch (step) {

      case 1:
        this.Recultamiento.saveSolicitante(form['value']).subscribe(res => {
          console.log(res);
          sessionStorage.setItem('idSolicitante', res);
        });
        break;
      case 2:
        if (sessionStorage.getItem('idSolicitante')) {
          const id = sessionStorage.getItem('idSolicitante');
          this.Recultamiento.saveContacto(id, form['value']).subscribe(res => {
            console.log(res);
          });
        }
        break;
      case 3:
        if (sessionStorage.getItem('idSolicitante')) {
          const id = sessionStorage.getItem('idSolicitante');
          this.Recultamiento.saveVacante(id, form['value']).subscribe(res => {
            console.log(res);
          });
        }
    }


  }

}
