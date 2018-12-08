import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Participante} from '../../../clb/models/participante';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import {IWorkBook, IWorkSheet, read, utils} from 'ts-xlsx';
import Swal from "sweetalert2";
import {ProyectoApoService} from '../../services/proyecto-apo.service';
import {HCAmpliado} from '../../models/hcampliado';

@Component({
  selector: 'app-haed-count2-apo',
  templateUrl: './haed-count2-apo.component.html',
  styleUrls: ['./haed-count2-apo.component.scss']
})
export class HaedCount2ApoComponent implements OnInit {

  @Output () responseHead = new EventEmitter();
  mobile = false;
  data: any[] = [];
  file: File;
  showTable = false;
  dataSource: HCAmpliado[] = [];
  proyects = [];
  filters = {
    idProyecto: 0
  };

  displayedColumns: string[] = [
    'OBJETIVO PUESTO',
    'FUNCIONES',
    'ACTIVIDADES',
    'NOMBRE DE META',
    'CANTIDAD META',
    'UNIDAD MEDIDA',
    'TIEMPO',
    'F.E.V.',


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

  constructor(breakpointObserver: BreakpointObserver, private ProyectApoService: ProyectoApoService) {
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
    this.ProyectApoService.getProyects().subscribe((res) => {
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
    this.mobile = true;
  }

  changeData(data, index) {

    this.dataSource[index] = new HCAmpliado();
    this.dataSource[index].idParticipante = data[0];
    this.dataSource[index].objetivoPuesto = data[1];
    this.dataSource[index].funciones = data[2];
    this.dataSource[index].actividades = data[3];
    this.dataSource[index].metaKpi = data[4];
    this.dataSource[index].cantidadMeta = data[5];
    this.dataSource[index].unidadMedida = data[6];
    this.dataSource[index].tiempo = data[7];
    this.dataSource[index].frecuenciaEval = data[8];

  }

  guardaHead() {
    let data = {
      participantes: this.dataSource,
      idProyecto: this.filters.idProyecto
    }
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectApoService.saveHeadAmp(data).subscribe(() => {
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

}
