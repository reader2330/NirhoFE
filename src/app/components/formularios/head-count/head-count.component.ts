import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';

@Component({
  selector: 'app-head-count',
  templateUrl: './head-count.component.html',
  styleUrls: ['./head-count.component.scss']
})
export class HeadCountComponent implements OnInit {
  mobile = false;
  ELEMENT_DATA: PeriodicElement[] = [
    {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
    {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
    {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
    {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
    {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
    {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
    {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
    {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
    {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
    {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
  ];
  dataSource = this.ELEMENT_DATA;

  displayedColumns: string[] = [
    'ID',
    'NIVEL#',
    'NOMBRES(S)',
    'APELLIDO PATERNO',
    'APELLIDO MATERNO',
    'GENERO',
    'RFC',
    'PUESTO',
    'FECHA DE INGRESO',
    'ANTIGUEDAD EN EL PUESTO',
    'NIVEL DE ESCOLARIDAD',
    'OTROS ESTUDIOS',
    'IDIOMA',
    'NIVEL',
    'CORREO ELECTRONICO',
    'SEDE',
    'AREA ORGANIZACIONAL'
  ];

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService) {
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

  readHead(file: any) {

  }




}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}
