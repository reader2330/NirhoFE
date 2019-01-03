import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatTableDataSource} from '@angular/material';
import {ActividadSolicitante} from '../../../screensOut/models/actividad-solicitante';
import {CatalogsService} from '../../clb/services/catalogs.service';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {Competencia} from '../models/competencia';
import {ConocimientoRYS} from '../models/conocimientoRYS';
import {ContactoRYS} from '../models/contacto-rys';

@Component({
  selector: 'app-candidato-form',
  templateUrl: './candidato-form.component.html',
  styleUrls: ['./candidato-form.component.scss']
})
export class CandidatoFormComponent implements OnInit {

  InformacionForm: FormGroup;
  ContactoForm: FormGroup;
  VacanteForm: FormGroup;
  nextFlag = false;
  CaracteristicasForm: FormGroup;
  showTable = false;
  paises = [];
  competencias = [];
  conocimientos = [];
  nivelCompetencias = [];
  tipoCompetencias = [];
  nacionalidades = [];
  habilidades = [];
  niveles = [];
  giros = [];
  motivos = [];
  contacts = [];
  idiomas = [];
  idiomasCandidato = [];
  dataSource = new MatTableDataSource<ActividadSolicitante>();
  contactos = [];
  newContacto = {
    nombre: null,
    tipo_contacto: null,
  };
  newIdioma = {
    nombre: null,
    habilidad: null,
    nivel: null,
  };
  newPuesto = {
    puesto: null,
    nivel: null,
    fechaFin: null,
    fechaIni: null,
    antiguedad: null,
  };
  newConocimiento = {
    nombre: null,
    descripcion: null,
    nivel: null,
    tipo: null,
  };

  actividades: ActividadSolicitante[] = [];
  displayContactos = ['nombre', 'tipo', 'detail1'];
  displayIdioma = ['idioma', 'nivel', 'habilidad', 'detail1'];
  displayCompetencias = ['nombreCompetencia', 'descripcionCompetencia', 'nivelCompetencia', 'detail1'];
  constructor(private _form: FormBuilder, private CatalogServices: CatalogsService, private Recultamiento: ReclutamientoService) { }

  ngOnInit() {
    this.InformacionForm = this._form.group({
      id: [null],
      rfc: [null, [Validators.required, Validators.maxLength(13)]],
      nombre: ['', Validators.required],
      nacionalidad: [null, Validators.required],
      nacimiento: [null, Validators.required],
      perfil: [null, Validators.required],
      situacion: [null, Validators.required],
      pretencion: [null, Validators.required],
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
    this.getContactos();
    this.getTipoCompetencia();
    this.getNivelCompetencia();
    this.getNacionalidades();
    this.getHabilidades();
  }

  addIdioma() {

  }



  removeIdioma(element){
    this.idiomasCandidato.map((item, i) => {
      if (item.nombre === element.nombre) {
        this.idiomasCandidato.splice(i, 1);
      }
    });
  }

  addPuesto()

  addContacto() {
    let id = sessionStorage.getItem('idCandidato');
    let contacto = new ContactoRYS();
    contacto.id = null;
    contacto.nombre = this.newContacto.nombre;
    contacto.tipo_contacto = this.newContacto.tipo_contacto;
    this.newContacto.nombre = null;
    this.newContacto.tipo_contacto = null;
    this.contactos.push(contacto);
    this.Recultamiento.saveContactoCandidato(id, contacto).subscribe(res => {

    });

  }
  removeContacto(elt) {
    this.contactos.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.contactos.splice(i, 1);
      }
    });
    this.Recultamiento.deleteContacto(elt.id).subscribe(res => {
    });
  }

  addConocimiento() {
    let id = sessionStorage.getItem('idVacante');
    let conocimiento = new ConocimientoRYS();
    conocimiento.id = null;
    conocimiento.nombre = this.newConocimiento.nombre;
    conocimiento.descripcion = this.newConocimiento.descripcion;
    conocimiento.nivel = this.newConocimiento.nivel;
    this.newConocimiento.descripcion = null;
    this.newConocimiento.nombre = null;
    this.newConocimiento.nivel = null;
    if (id) {
      this.Recultamiento.saveConocimiento(id, conocimiento).subscribe(res => {
        this.conocimientos.push(conocimiento);
      });
    }
  }
  removeConocimiento(elt) {
    console.log(elt);
    this.conocimientos.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.conocimientos.splice(i, 1);
      }
    });
    this.Recultamiento.deleteConocimiento(elt.id).subscribe(res => {
    });
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
    this.CatalogServices.getTypeContactEmpleado().subscribe(res => {
      this.contacts = res;
    });
  }
  getMotivos() {
    this.CatalogServices.getMotivos().subscribe(res => {
      console.log(res);
      this.motivos = res;
    });
  }
  getNivelCompetencia(){
    this.CatalogServices.getNivelCompetencia().subscribe(res => {
      this.nivelCompetencias = res
    });
  }
  getTipoCompetencia() {
    this.CatalogServices.getTipoCompetencia().subscribe(res => {
      this.tipoCompetencias = res;
    });
  }
  getNacionalidades(){
    this.CatalogServices.getNacionalidades().subscribe(res => {
      this.nacionalidades = res;
    });
  }
  getHabilidades() {
    this.CatalogServices.getHabilidades().subscribe(res => {
      this.habilidades = res;
    });
  }
  searchByRFC() {
    if (this.InformacionForm.value['rfc']) {
      const rfc = this.InformacionForm.value['rfc'];
      this.Recultamiento.getSolicitanteByRFC(rfc).subscribe(res => {
        console.log(res);
        if (res.length) {
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
            if (res[0]['vacantes'][0] && res[0]['vacantes'][0]['competencias'].length ) {
              this.competencias = res[0]['vacantes'][0]['competencias'];
            }
            if (res[0]['vacantes'][0] && res[0]['vacantes'][0]['conocimientos'].length ) {
              this.conocimientos = res[0]['vacantes'][0]['conocimientos'];
            }

          }

          sessionStorage.setItem('idSolicitante', this.InformacionForm.value['id']);
          this.nextFlag = true;
        }

      });
    }
  }
  guardarForm (form , step) {

    switch (step) {

      case 1:
        this.Recultamiento.saveCandidato(form['value']).subscribe(res => {
          console.log(res);
          sessionStorage.setItem('idCandidato', JSON.stringify(res));
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
