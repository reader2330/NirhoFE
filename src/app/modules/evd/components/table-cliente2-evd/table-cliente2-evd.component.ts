import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  symbol1: string;
  symbol2: string;
  symbol3: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne', symbol1: 'H', symbol2: 'H', symbol3: 'H'},
];

@Component({
  selector: 'app-table-cliente2-evd',
  templateUrl: './table-cliente2-evd.component.html',
  styleUrls: ['./table-cliente2-evd.component.scss']
})
export class TableCliente2EvdComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'symbol1', 'symbol2', 'symbol3'];
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
