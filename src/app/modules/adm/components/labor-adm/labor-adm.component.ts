import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {LanguageModalAdmComponent} from '../language-modal-adm/language-modal-adm.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../../../irh/components/reviews/training-irh/training-irh.component';
import {LaborModalAdmComponent} from '../labor-modal-adm/labor-modal-adm.component';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import Swal from "sweetalert2";

export interface laboral_interface {
  puesto: string;
  nivelLaboral: number;
  fechaInicio: number;
  fechaTermino: string;
  antiguedad: string;
  localidad: string;
  area: string;
}

@Component({
  selector: 'app-labor-adm',
  templateUrl: './labor-adm.component.html',
  styleUrls: ['./labor-adm.component.scss']
})
export class LaborAdmComponent implements OnInit {

  jsonFinal = {
    id: null,
    nombreCompleto : '',
    nacionalidad: 0,
    fechaNacimiento: null,
    edad : 0,
    rfc: '',
    curp: '',
    nss: '',
    direccion: '',
    contactos : [],
    banco : 0,
    bancoCuenta: null,
    bancoClaveInterbancaria : 0,
    escolaridad: 0,
    escolaridadCarrera: '',
    escolaridadEspecialidad: '',
    escolaridadCapacidades : '',
    escolaridadCertificaciones : [],
    escolaridadCursos: [],
    escolaridadOficios: [],
    titulo: false,
    idiomas : [],
    puesto: '',
    nivelLaboral: 0,
    fechaInicio: null,
    fechaTermino: null,
    antiguedad: 0,
    localidad: '',
    area: '',
    documentoCurp: null,
    documentoIne : null,
    documentoCV: null,
    documentoComprobanteDomicilio: null
  };
  displayedColumns: string[] = ['puesto', 'nivelLaboral', 'fechaInicio', 'fechaTermino', 'antiguedad', 'localidad', 'area', 'delete'];
  laborales: laboral_interface[] = [] ;
  dataSource = [];
  mobile = false;
  temp = '';

  laborForm = new FormGroup(
    {
      idioma: new FormControl(0, [Validators.required]),
      nivel: new FormControl(0, Validators.required),
      habilidades: new FormControl(0, Validators.required)
    }
  );

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, public dialog: MatDialog, private CatalogsAdmService: CatalogsAdmService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
        //this.checkMobileCols();
      } else {
        this.mobile = false;
        //this.checkMobileCols();
      }
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(LaborModalAdmComponent, {
      width: '750px',
      height: 'auto'
      //data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getFieldsJob();
      console.log('The dialog was closed');

    });

  }

  getFieldsJob() {
    //console.log("entaaaa")
    this.temp = JSON.parse(sessionStorage.getItem('job_detail'));
    this.laborales.push(this.temp);
    this.dataSource = this.laborales;
    //console.log("temp: ", this.dataSource);
  }
  // accent
  ngOnInit() {
  }

  saveEmpleado() {
    console.log(sessionStorage);
    let employee = JSON.parse(sessionStorage.getItem('employee'));
    let laborList = JSON.parse(sessionStorage.getItem('labor-list'));
    let languageList = JSON.parse(sessionStorage.getItem('language-list'));
    let contacto = JSON.parse(sessionStorage.getItem('contacto'));
    let payment = JSON.parse(sessionStorage.getItem('payment'));
    let scholarship = JSON.parse(sessionStorage.getItem('scholarship'));
    this.jsonFinal.id = employee.id;
    this.jsonFinal.nombreCompleto = employee.nombreCompleto;
    this.jsonFinal.nacionalidad = employee.nacionalidad;
    this.jsonFinal.fechaNacimiento = employee.fechaNacimiento;
    this.jsonFinal.edad = employee.edad;
    this.jsonFinal.rfc = employee.rfc;
    this.jsonFinal.curp = employee.curp;
    this.jsonFinal.nss = employee.nss;
    this.jsonFinal.direccion = employee.direccion;
    this.jsonFinal.contactos.push(contacto);
    this.jsonFinal.banco = payment.banco;
    this.jsonFinal.bancoCuenta = payment.bancoCuenta;
    this.jsonFinal.bancoClaveInterbancaria = payment.bancoClaveInterbancaria;
    this.jsonFinal.escolaridad = scholarship.escolaridad;
    this.jsonFinal.escolaridadCarrera = scholarship.escolaridadCarrera;
    this.jsonFinal.escolaridadEspecialidad = scholarship.escolaridadEspecialidad;
    this.jsonFinal.escolaridadCertificaciones = scholarship.escolaridadCertificaciones;
    this.jsonFinal.escolaridadCursos = scholarship.escolaridadCursos;
    this.jsonFinal.escolaridadOficios = scholarship.escolaridadOficios;
    this.jsonFinal.titulo = scholarship.titulo;
    this.jsonFinal.idiomas.push(languageList);
    this.jsonFinal.puesto = this.laborales[0].puesto;
    this.jsonFinal.nivelLaboral = this.laborales[0].nivelLaboral;
    this.jsonFinal.fechaInicio = this.laborales[0].fechaInicio;
    this.jsonFinal.fechaTermino = this.laborales[0].fechaTermino;
    this.jsonFinal.antiguedad = this.laborales[0].antiguedad;
    this.jsonFinal.localidad = this.laborales[0].localidad;
    this.jsonFinal.area = this.laborales[0].area;

    this.CatalogsAdmService.saveEmploye(this.jsonFinal).subscribe(res => {
      console.log(res);
      Swal(
        'Listo.',
        'Los cuestionarios se enviaron correctamente',
        'success'
      ).then(res => {
        this.response.emit({value:1});
      });
    });



  }

}
  /*@Component({
    selector: 'app-labor-modal-adm',
    templateUrl: '../labor-modal-adm/labor-modal-adm.component.html'
  })*/

