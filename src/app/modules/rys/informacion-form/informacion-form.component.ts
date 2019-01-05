import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../clb/services/catalogs.service';
import {MatTableDataSource} from '@angular/material';
import {ActividadSolicitante} from '../../../screensOut/models/actividad-solicitante';
import {ReclutamientoService} from '../services/reclutamiento.service';
import {Competencia} from '../models/competencia';
import {ConocimientoRYS} from '../models/conocimientoRYS';
import Swal from "sweetalert2";


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
  conocimientos = [];
  nivelCompetencias = [];
  tipoCompetencias = [];
  niveles = [];
  giros = [];
  motivos = [];
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
  newConocimiento = {
    nombre: null,
    descripcion: null,
    nivel: null,
    tipo: null,
  };

  actividades: ActividadSolicitante[] = [];
  displayActividades = ['nombreActividad', 'descripcionActividad', 'nivel', 'detail1'];
  displayCompetencias = ['nombreCompetencia', 'descripcionCompetencia', 'nivelCompetencia', 'detail1'];
  displayPuestos = ['puesto', 'nivel', 'fechaInicio', 'fechaFinal', 'antiguedad','' ]
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
    this.getMotivos();
    this.getTipoCompetencia();
    this.getNivelCompetencia();
  }

  addCompetencia() {
    let id = sessionStorage.getItem('idVacante');
    let competencia = new Competencia();
    competencia.nombre = this.newCompetencia.nombre;
    competencia.descripcion = this.newCompetencia.descripcion;
    competencia.nivel = this.newCompetencia.nivel;
    competencia.tipo = this.newCompetencia.tipo;
    if (id){
      this.Recultamiento.saveCompetencia(id, competencia).subscribe(res => {
        this.competencias.push(competencia);
      });
    }
  }
  removeCompetencia(comp) {
    this.competencias.map((item, i ) => {
      if (item.nombre === comp.nombre) {
        this.competencias.splice(i, 1);
      }
    });
    this.Recultamiento.deleteCompetencias(comp.id).subscribe(res => {
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
    this.CatalogServices.getTypeContact().subscribe(res => {
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
          if (res[0]['vacantes'].length) {
            sessionStorage.setItem('idVacante', res[0]['vacantes'][0]['id']);
            this.VacanteForm.patchValue(res[0]['vacantes'][0]);
            if (res[0]['vacantes'][0] && res[0]['vacantes'][0]['actividades'].length ) {
              console.log(res[0]['vacantes'][0]['actividades']);
              this.actividades = res[0]['vacantes'][0]['actividades'];

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

  mostrarForm(form: any) {
    console.log(form['value']);
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
            this.Recultamiento.saveSolicitante(form['value']).subscribe(res => {
              console.log(res);
              sessionStorage.setItem('idSolicitante', JSON.stringify(res));
              Swal(
                'Listo.',
                'La información se guardo correctamente',
                'success'
              );
            });
            break;
          case 2:
            if (sessionStorage.getItem('idSolicitante')) {
              const id = JSON.parse(sessionStorage.getItem('idSolicitante'));
              if (id.id) {
                this.Recultamiento.saveContacto(id.id, form['value']).subscribe(res => {
                  console.log(res);
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );
                });
              } else {
                this.Recultamiento.saveContacto(id, form['value']).subscribe(res => {
                  console.log(res);
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
            if (sessionStorage.getItem('idSolicitante')) {
              console.log(form['value']);
              const id = JSON.parse(sessionStorage.getItem('idSolicitante'));
              if (id.id) {
                this.Recultamiento.saveVacante(id.id, form['value']).subscribe(res => {
                  console.log(res);
                  sessionStorage.setItem('idVacante', JSON.stringify(res));
                  Swal(
                    'Listo.',
                    'La información se guardo correctamente',
                    'success'
                  );

                });
              } else {
                this.Recultamiento.saveVacante(id, form['value']).subscribe(res => {
                  console.log(res);
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
            if (sessionStorage.getItem('idVacante')) {
              let id = JSON.parse(sessionStorage.getItem('idVacante'));
              this.Recultamiento.saveCaracteristicas(id, form['value']).subscribe(res => {
                console.log(res);
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


}
