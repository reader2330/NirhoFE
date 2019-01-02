import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../clb/services/catalogs.service';
import {MatTableDataSource} from '@angular/material';
import {ActividadSolicitante} from '../../../screensOut/models/actividad-solicitante';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {Competencia} from '../models/competencia';


@Component({
  selector: 'app-informacion-form',
  templateUrl: './informacion-form.component.html',
  styleUrls: ['./informacion-form.component.scss']
})
export class InformacionFormComponent implements OnInit {
  InformacionForm: FormGroup;
  ContactoForm: FormGroup;
  VacanteForm: FormGroup;
  nextFlag = false;
  CaracteristicasForm: FormGroup;
  showTable = false;
  paises = [];
  competencias = [];
  niveles = [];
  giros = [];
  dataSource = new MatTableDataSource<ActividadSolicitante>();
  contacts = [];
  newActividad = {
    nombreActividad: null,
    descripcionActividad: null,
    nivel: null
  };
  newCompetencia = {
    nombre: null,
    descripcion: null,
    nivel: null,
    tipo: null,
  };

  actividades: ActividadSolicitante[] = [];
  displayActividades = ['nombreActividad', 'descripcionActividad', 'nivel', 'detail1'];
  displayCompetencias = ['nombreActividad', 'descripcionActividad', 'nivel','tipo' ,'detail1'];
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
      nombre: ['', Validators.required],
      puesto: ['', Validators.required],
      tipoContacto: [null, Validators.required],
      email: ['', Validators.required],
      telefono: ['', Validators.required],
      celular: ['', Validators.required]
    });
    this.VacanteForm = this._form.group({
      nombreVacante: ['', Validators.required],
      giro: [null, Validators.required],
      motivo: [null, Validators.required],
      numVacantes: [null, Validators.required],
      puesto: ['', Validators.required],
      puestoReporta: ['', Validators.required],
      aniosExperiencia: ['', Validators.required],
      puestoCargo: ['', Validators.required]

    });
    this.CaracteristicasForm = this._form.group({
      genero: [null, Validators.required],
      estadoCivil: [null, Validators.required],
      dispoViajar: [null, Validators.required],
      cambioResidencia: [null, Validators.required],
      necesidadesEspeciales: [null, Validators.required],
      minEdad: [null, Validators.required],
      maxEdad: [null, Validators.required],
      caractAdicionales: [null, Validators.required],
      gradoEstudios: [null, Validators.required],
      institucion: [null, Validators.required],
      titulo: [null, Validators.required],
      carrera: [null, Validators.required],
      especialidad: [null, Validators.required],
      certificaciones: [null, Validators.required],
      cursos: [null, Validators.required],
      oficios: [null, Validators.required],
      oCapacidades: [null, Validators.required]

    });
    this.getPaises();
    this.getGiros();
    this.getContactos();
    this.getEstudios();

  }

  addCompetencia() {
    let id = sessionStorage.getItem('idVacante');
    let competencia = new Competencia();
    competencia.id = null;
    competencia.nombre = this.newCompetencia.nombre;
    competencia.descripcion = this.newCompetencia.descripcion;
    competencia.nivel = this.newCompetencia.nivel;
    competencia.tipo = this.newCompetencia.tipo;
    if (id){
      this.Recultamiento.saveCompetencia(id, competencia).subscribe(res => {
        console.log(res);
        this.competencias.push(competencia);
      });
    }
  }
  removeCompetencia(comp){
    this.competencias.map((item, i ) => {
      if (item.nombre === comp.nombre) {
        this.actividades.splice(i, 1);
      }
    });
    this.Recultamiento.deleteActividad(comp.id).subscribe(res => {
      console.log(res);
    });
  }

  addActividad() {
    let id = sessionStorage.getItem('idVacante');
    let actividad = new ActividadSolicitante();
    actividad.nombre = this.newActividad.nombreActividad;
    actividad.descripcion = this.newActividad.descripcionActividad;
    actividad.nivel = this.newActividad.nivel;
    this.newActividad.descripcionActividad = null;
    this.newActividad.nombreActividad = null;
    this.newActividad.nivel = null;
    if (id) {
      this.Recultamiento.saveActividad(id, actividad).subscribe(res => {
        this.actividades.push(actividad);
        this.dataSource.data = this.actividades;
        this.showTable = true;
      });
    }

  }
  removeActividad(elt) {
    this.actividades.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.actividades.splice(i, 1);
      }
    });
    this.Recultamiento.deleteActividad(elt.id).subscribe(res => {
      console.log(res);
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
  getEstudios() {
    this.CatalogServices.getScholarship().subscribe(res => {
      console.log(res);
      this.niveles = res;
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
        if(res.length) {
          this.InformacionForm.patchValue(res[0]);
          if (res[0]['contactos']) {
            this.ContactoForm.patchValue(res[0]['contactos'][0]);
          }
          if (res[0]['vacantes']) {
            sessionStorage.setItem('idVacante', res[0]['vacantes'][0]['id']);
            this.VacanteForm.patchValue(res[0]['vacantes'][0]);
            if (res[0]['vacantes'][0] && res[0]['vacantes'][0]['actividades'].length ) {
              console.log(res[0]['vacantes'][0]['actividades']);
              this.actividades = res[0]['vacantes'][0]['actividades'];
              this.dataSource.data = this.actividades;
            }
            if (res[0]['vacantes'][0]['caracteristicas'].length) {
              this.CaracteristicasForm.patchValue(res[0]['vacantes'][0]['caracteristicas'][0]);
            }
          }

          sessionStorage.setItem('idSolicitante', this.InformacionForm.value['id']);
          this.nextFlag = true;
        }

      });
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
          sessionStorage.setItem('idSolicitante', JSON.stringify(res));
        });
        break;
      case 2:
        if (sessionStorage.getItem('idSolicitante')) {
          const id = JSON.parse(sessionStorage.getItem('idSolicitante'));
          if (id.id) {
            this.Recultamiento.saveContacto(id.id, form['value']).subscribe(res => {
              console.log(res);
            });
          } else {
            this.Recultamiento.saveContacto(id, form['value']).subscribe(res => {
              console.log(res);
            });
          }
        }
        break;
      case 3:
        if (sessionStorage.getItem('idSolicitante')) {
          console.log(form['value']);
          const id = JSON.parse(sessionStorage.getItem('idSolicitante'));
          if (id.id) {
            this.Recultamiento.saveVacante(id.id, form['value']).subscribe(res => {
              console.log(res);
            });
          } else {
            this.Recultamiento.saveVacante(id, form['value']).subscribe(res => {
              console.log(res);
            });
          }
        }
        break;
      case 4:
        if (sessionStorage.getItem('idVacante')) {
          let id = sessionStorage.getItem('idVacante');
          this.Recultamiento.saveCaracteristicas(id, form['value']).subscribe(res => {
            console.log(res);
          });
        }
        break;
    }
  }


}
