import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-aviso',
  templateUrl: './aviso.component.html',
  styleUrls: ['./aviso.component.scss']
})
export class AvisoComponent implements OnInit {

  constructor(public modal: MatDialog) { }

  ngOnInit() {
  }

  cerrarModal() {
    this.modal.closeAll();
  }

}
