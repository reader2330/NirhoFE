import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-selector-areas',
  templateUrl: './selector-areas.component.html',
  styleUrls: ['./selector-areas.component.scss']
})
export class SelectorAreasComponent implements OnInit {
  proyect = {};
  proyects = [];
  area = '';
  areas = [];
  constructor() { }

  ngOnInit() {
  }

  addArea() {
    let add = true;
    for(let area of this.areas){

    }

    this.areas.push(this.area);
    this.area = '';
  }

}
