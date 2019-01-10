import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatTableDataSource} from '@angular/material';
import {ActividadSolicitante} from '../../../screensOut/models/actividad-solicitante';
import {CatalogsService} from '../../clb/services/catalogs.service';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {Competencia} from '../models/competencia';
import {ConocimientoRYS} from '../models/conocimientoRYS';
import {ContactoRYS} from '../models/contacto-rys';
import {IdiomaRYS} from '../models/idioma-rys';
import {PuestoRYS} from '../models/puesto-rys';
import Swal from "sweetalert2";
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'app-candidato-form',
  templateUrl: './candidato-form.component.html',
  styleUrls: ['./candidato-form.component.scss']
})
export class CandidatoFormComponent implements OnInit {
  @Output() response = new EventEmitter();
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
  habilidadesNivel = [];
  nivelesIdiomas = [];
  puestos = [];
  dataSource = new MatTableDataSource<ActividadSolicitante>();
  contactos = [];
  newContacto = {
    nombre: null,
    tipoContacto: null,
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
  displayPuestos = ['puesto', 'nivel', 'fechaFin', 'fechaIni', 'antiguedad', 'detail1'];
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
      direccion: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      email: [null, Validators.required]

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
      id: [null],
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
    this.getIdiomas();
    this.getEstudios();
    this.getHabilidadesNiveles();
    this.getNivelesIdioma();
  }

  addIdioma() {
    this.showTable = false;
    let id = JSON.parse(sessionStorage.getItem('idCandidato'));
    let idioma = new IdiomaRYS();
    idioma.nombre = this.newIdioma.nombre;
    idioma.habilidad = this.newIdioma.habilidad;
    idioma.nivel = this.newIdioma.nivel;
    this.newIdioma.nombre = null;
    this.newIdioma.habilidad = null;
    this.newIdioma.nivel = null;
    this.Recultamiento.saveIdioma(id.id, idioma).subscribe(res => {
      idioma.id = res.id;
      this.idiomasCandidato.push(idioma);
      this.showTable = true;
    });
  }

  getIdiomas() {
    this.CatalogServices.getIdiomas().subscribe(res => {
      this.idiomas = res;
    });
  }



  removeIdioma(elt) {
    this.showTable = false;
    this.idiomasCandidato.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.idiomasCandidato.splice(i, 1);
      }
    });
    this.Recultamiento.deleteIdioma(elt.id).subscribe(res => {
      this.showTable = true;
    });
  }

  addPuesto() {
    this.showTable = false;
    let id = JSON.parse(sessionStorage.getItem('idCandidato'));
    let puesto = new PuestoRYS();
    puesto.puesto = this.newPuesto.puesto;
    puesto.nivel = this.newPuesto.nivel;
    puesto.fechaIni = this.newPuesto.fechaIni;
    puesto.fechaFin = this.newPuesto.fechaFin;
    puesto.antiguedad = this.newPuesto.antiguedad;
    for (let key in this.newPuesto) {
      this.newPuesto[key] = null;
    }
    this.Recultamiento.savePuestos(id.id, puesto).subscribe(res => {
      puesto.id = res['id'];
      this.puestos.push(puesto);
      this.showTable = true;
    });
  }

  addContacto() {
    this.showTable = false;
    let id = JSON.parse(sessionStorage.getItem('idCandidato'));
    let contacto = new ContactoRYS();
    contacto.id = null;
    contacto.nombre = this.newContacto.nombre;
    contacto.tipoContacto = this.newContacto.tipoContacto;
    this.newContacto.nombre = null;
    this.newContacto.tipoContacto = null;
    this.Recultamiento.saveContactoCandidato(id.id, contacto).subscribe(res => {
      console.log(res);
      contacto.id = res.id;
      this.contactos.push(contacto);
      this.showTable = true;
    });

  }
  mostrarDescripcion(catalog, element) {
    let desc = '';
    switch (catalog) {
      case 1:
        this.contacts.map((item, i) => {
          if (item.id === element) {
            desc = item.descripcionCatalogo;
            return;
          }
        });
        break;
      case 2:
        this.nivelesIdiomas.map((item, i) => {
          if (item.id === element){
            desc = item.descripcionCatalogo;
            return;
          }
        });
        break;
      case 3:
        this.nivelCompetencias.map((item, id) => {
          if (item.id === element) {
            desc = item.descripcionCatalogo;
            return;
          }
        });
        break
    }
    return desc;
  }
  removeContacto(elt) {
    this.showTable = false;
    this.contactos.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.contactos.splice(i, 1);
      }
    });
    this.Recultamiento.deleteContacto(elt.id).subscribe(res => {
      this.showTable = true;
    });
  }
  removePuesto(elt) {
    this.showTable = true;
    this.puestos.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.puestos.splice(i, 1);
      }
    });
    this.Recultamiento.deletePuesto(elt.id).subscribe(res => {
      this.showTable = false;
    });
  }

  addConocimiento() {
    this.showTable = false;
    let id = JSON.parse(sessionStorage.getItem('idCandidato'));
    let conocimiento = new ConocimientoRYS();
    conocimiento.id = null;
    conocimiento.nombre = this.newConocimiento.nombre;
    conocimiento.descripcion = this.newConocimiento.descripcion;
    conocimiento.nivel = this.newConocimiento.nivel;
    this.newConocimiento.descripcion = null;
    this.newConocimiento.nombre = null;
    this.newConocimiento.nivel = null;
    if (id) {
      this.Recultamiento.saveConocimientoCandidato(id.id, conocimiento).subscribe(res => {
        conocimiento.id = res.id;
        this.conocimientos.push(conocimiento);
        this.showTable = true;
      });
    }
  }
  removeConocimiento(elt) {
    this.showTable = false;
    this.conocimientos.map((item, i ) => {
      if (item.nombre === elt.nombre) {
        this.conocimientos.splice(i, 1);
      }
    });
    this.Recultamiento.deleteConocimientoCandidato(elt.id).subscribe(res => {
      this.showTable = true;
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
  getNivelesIdioma() {
    this.CatalogServices.getNivelesIdioma().subscribe(res => {

      this.nivelesIdiomas = res;
    });
  }
  getHabilidadesNiveles() {
    this.CatalogServices.getHabilidadesIdioma().subscribe(res => {
      this.habilidadesNivel = res;
    });

  }
  searchByRFC() {
    if (this.InformacionForm.value['rfc']) {
      const rfc = this.InformacionForm.value['rfc'];
      this.Recultamiento.getCandidatoRFC(rfc).subscribe(res => {
        if (res) {
          this.InformacionForm.patchValue(res);
          this.InformacionForm.patchValue({
            'nacimiento': new Date(this.InformacionForm.value['nacimiento'])
          });
          if (res['contactos']) {
              this.contactos = res['contactos'];
              this.showTable = true;
          }
          if (res['idiomas']) {
            this.idiomasCandidato = res['idiomas'];
            this.showTable = true;
          }
          if (res['conocimentos']) {
            this.conocimientos = res['conocimentos'];
            this.showTable = true;
          }
          if (res['puestos']) {
            this.puestos = res['puestos'];
            this.showTable = true;
          }
          if (res['caracteristicasCandidatoCv']){
            this.CaracteristicasForm.patchValue(res['caracteristicasCandidatoCv']);
          }
          let json = {
            id: this.InformacionForm.value['id']
          };
          sessionStorage.setItem('idCandidato', JSON.stringify(json));
          this.nextFlag = true;
        }

      });
    }
  }
  guardarForm (form , step) {

    Swal({
      title: '',
      text: 'Seguro que quieres guardar los datos',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        switch (step) {
          case 1:
            this.Recultamiento.saveCandidato(form['value']).subscribe(res => {
              Swal(
                'Listo.',
                'La información se guardo correctamente',
                'success'
              );
              sessionStorage.setItem('idCandidato', JSON.stringify(res));
            });
            break;
          case 2:
            if (sessionStorage.getItem('idCandidato')) {
              const id = JSON.parse(sessionStorage.getItem('idCandidato'));
              if (id.id) {
                this.Recultamiento.saveContacto(id.id, form['value']).subscribe(res => {
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );
                });
              } else {
                this.Recultamiento.saveContacto(id, form['value']).subscribe(res => {
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );
                });
              }
            }
            break;
          case 3:
            if (sessionStorage.getItem('idCandidato')) {
              const id = JSON.parse(sessionStorage.getItem('idCandidato'));
              if (id.id) {
                this.Recultamiento.saveVacante(id.id, form['value']).subscribe(res => {
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );

                });
              } else {
                this.Recultamiento.saveVacante(id, form['value']).subscribe(res => {
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );
                });
              }
            }
            break;
          case 4:
            if (sessionStorage.getItem('idCandidato')) {
              let id = JSON.parse(sessionStorage.getItem('idCandidato'));
              this.Recultamiento.saveCaractesristicasCandidato(id.id, form['value']).subscribe(res => {

                Swal(
                  'Listo.',
                  'La información se guardo correctamente',
                  'success'
                );
              });
            }
            break;
        }
      }
    });
  }

  goInicio() {
    this.response.emit({value: 1});
  }

}
