import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';
import {IWorkBook, read, readFile, utils, IWorkSheet} from 'ts-xlsx';
import {ProyectoService} from '../../../services/proyecto.service';
import {Participante} from '../../../models/participante';


@Component({
  selector: 'app-head-count',
  templateUrl: './head-count.component.html',
  styleUrls: ['./head-count.component.scss']
})
export class HeadCountComponent implements OnInit {
  mobile = false;
  data = [];
  file: File;
  showTable = false;
  dataSource: Participante[] = [];
  proyects = [];
  filters = {
    idProyecto: 0
  }

  displayedColumns: string[] = [
    'NIVEL TEXTO',
    'NOMBRES',
    'APELLIDO PATERNO',
    'APELLIDO MATERNO',
    'GENERO',
    'PUESTO',
    'FECHA DE INGRESO',
    'IDIOMA',
    'NIVEL',
    'CORREO ELECTRONICO',
    'SEDE',

  ];
  names = [
    'id',
  'nivel', 'nivelTexto',
  'nombres',
  'aPaterno',
  'aMaterno',
  'genero',
  'rfc',
  'puesto',
  'fechaIngreso',
  'antigPuesto',
  'nivelEscolaridad',
  'otrosEstudios',
  'idioma',
  'nivelIdioma',
  'correoElectronico',
  'sede',
  'areaOrg'
  ]

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private ProyectService:ProyectoService) {
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
  this.getProyects();

  }

  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }


  checkMaxColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMaxTwoColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  readFile(evt: any) {
    const target: DataTransfer = <DataTransfer>(evt.target);
    if (target.files.length === 1 && evt.target.accept === ".xlsx") {
      const reader: FileReader = new FileReader();
      reader.onload = (e: any) => {
        /* read workbook */
        const bstr: string = e.target.result;
        const wb: IWorkBook = read(bstr, {type: 'binary'});
        const wsname: string = wb.SheetNames[0];
        const ws: IWorkSheet = wb.Sheets[wsname];
        /* save data */
        this.data = <any>(utils.sheet_to_json(ws, {header: 1}));
        this.data.shift();
        this.dataSource = this.data;
        for (let i = 0; i < this.data.length; i++) {
          for (let j = 0; j < 18; j++ ) {
            this.dataSource[i][this.getName(j)] = this.dataSource[i][j];
          }
        }
        console.log(this.dataSource);
      };
      reader.readAsBinaryString(target.files[0]);

    }
  }

  getName(j){
    return this.names[j];
  }

  uploadHeadCount() {
    this.showTable = true;
  }
  guardaHead() {
      let data = {
        lista: this.dataSource,
        idProyecto: this.filters.idProyecto
      }
      this.ProyectService.saveHead(data).subscribe((res) => {
        console.log(res);
      });
  }




}

