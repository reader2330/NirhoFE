import {Component, Inject, OnInit} from '@angular/core';
import {LanguageModalAdmComponent} from '../language-modal-adm/language-modal-adm.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../../../irh/components/reviews/training-irh/training-irh.component';
import {LaborModalAdmComponent} from '../labor-modal-adm/labor-modal-adm.component';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  ini: number;
  ini2: number;
  ini3: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', ini: 1, ini2: 2, ini3: 3},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', ini: 1, ini2: 2, ini3: 3}
];

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
  displayedColumns: string[] = ['puesto', 'nivel', 'date_init', 'date_end', 'antiquity', 'location', 'area', 'delete'];
  dataSource = ELEMENT_DATA;

  constructor(public dialog: MatDialog) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(LaborModalAdmComponent, {
      width: '750px',
      height: 'auto'
      //data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

    });
  }

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

    console.log(this.jsonFinal);


  }

}

@Component({
  selector: 'app-labor-modal-adm',
  templateUrl: '../labor-modal-adm/labor-modal-adm.component.html'
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
