import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {IWorkBook, read, utils, IWorkSheet} from 'ts-xlsx';
import Swal from "sweetalert2";
import {HCPVC} from '../../models/hcpvc';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {ProyectoPVCService} from '../../services/proyectoPVC.service';


@Component({
  selector: 'app-head-count-pvc',
  templateUrl: './head-count-pvc.component.html',
  styleUrls: ['./head-count-pvc.component.scss']
})
export class HeadCountPvcComponent implements OnInit {
  @Output () responseHead = new EventEmitter();
  mobile = false;
  data: any[] = [];
  file: File;
  showTable = false;
  dataSource: HCPVC[] = [];
  proyects = [];
  filters = {
    idProyecto: 0
  };

  displayedColumns: string[] = [
    'NIVEL TEXTO',
    'NOMBRES',
    'APELLIDO PATERNO',
    'APELLIDO MATERNO',
    'GENERO',
    'PUESTO',
    'FECHA DE INGRESO',


  ];
  names = [
    'id',
  'nivel',
    'nivelTexto',
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
  ];

  constructor(breakpointObserver: BreakpointObserver, private ProyectService: ProyectoPVCService) {
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
        this.data = <any[]>(utils.sheet_to_json(ws, {header: 1}));
        this.data.shift();
        for (let  i = 0; i < this.data.length; i++) {
          for (let j = 0 ; j < this.data[i].length; j++) {
            this.changeData(this.data[i], i);
          }
        }
        console.log(this.dataSource);
      };
      reader.readAsBinaryString(target.files[0]);

    }
  }

  getName(j) {
    return this.names[j];
  }

  uploadHeadCount() {
    this.showTable = true;
  }
  guardaHead() {
    let data = {
      participantes: this.dataSource,
      idProyecto: this.filters.idProyecto
    };
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectService.saveHead(data).subscribe(() => {
            Swal(
              'Listo.',
              'La información se guardo correctamente',
              'success'
            ).then(() => {
              this.responseHead.emit({value: 1});
            });

          },
          (err) => {
            console.log(err);
            Swal(
              'Algo salio mal.',
              'No se pudo guarda la información',
              'error'
            ).then(() => {
              this.responseHead.emit({value: 1});
            });
          });
      }
    });
  }
  changeData(data, index) {
    this.dataSource[index] = new HCPVC();
    this.dataSource[index].idParticipante = data[0];
    this.dataSource[index].nivel = data[1];
    this.dataSource[index].nivelTexto = data[2];
    this.dataSource[index].nombres = data[3];
    this.dataSource[index].aPaterno = data[4];
    this.dataSource[index].aMaterno = data[5];
    this.dataSource[index].genero = data[6];
    this.dataSource[index].rfc = data[7];
    this.dataSource[index].puesto = data[8];
    this.dataSource[index].fechaIngreso = data[9];
    this.dataSource[index].antigPuesto = data[10];
    this.dataSource[index].nivelEscolaridad = data[11];
    this.dataSource[index].otrosEstudios = data[12];
    this.dataSource[index].idioma = data[13];
    this.dataSource[index].nivelIdioma = data[14];
    this.dataSource[index].correoElectronico = data[15];
    this.dataSource[index].sede = data[16];
    this.dataSource[index].areaOrg = data[17];
    this.dataSource[index].idJefeInmediato = data[18];
    this.dataSource[index].area = data[19];
    this.dataSource[index].esfera = data[20];
    this.dataSource[index].nivel_p = data[21];
    this.dataSource[index].especialidad = data[22];

  }




}
