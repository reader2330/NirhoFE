import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  simbol1: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', symbol: 'H'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li', symbol: 'H'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be', symbol: 'H'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B', symbol: 'H'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C', symbol: 'H'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N', symbol: 'H'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O', symbol: 'H'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F', symbol: 'H'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne', symbol: 'H'},
];

@Component({
  selector: 'app-table-cliente-irh',
  templateUrl: './table-cliente-evd.component.html',
  styleUrls: ['./table-cliente-evd.component.scss']
})
export class TableClienteEvdComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'symbol1'];
  dataSource = ELEMENT_DATA;

  enterpriseForm = new FormGroup(
    {
      rfc: new FormControl('', Validators.required)
    }
  );

  constructor() { }

  ngOnInit() {
  }

}
