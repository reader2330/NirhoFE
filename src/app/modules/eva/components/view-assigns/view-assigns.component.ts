import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CatalogsService } from '../../../clb/services/catalogs.service';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatTableDataSource } from '@angular/material';
import {Proyecto360Service} from '../../services/proyecto360.service';

@Component({
  selector: 'app-view-assigns',
  templateUrl: './view-assigns.component.html',
  styleUrls: ['./view-assigns.component.scss']
})
export class ViewAssignsComponent implements OnInit {
  mobile = false;
  evaluators = [];
  displayedColumns: string[] = ['evaluado'];
  proyect = {};
  proyects = [];
  showAssign = false;
  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private ProyectServices:Proyecto360Service) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait
    ]).subscribe(result => {
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

  getAsignacion() {
    this.showAssign = false;
    this.ProyectServices.getAsignacion(this.proyect['idProyecto']).subscribe(res => {
      console.log(res);
      this.evaluators = res;
      this.showAssign = true;
    });

  }

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }



  getProyects() {
    this.ProyectServices.getProyects().subscribe(res => {

      this.proyects = res;
    });
  }

}
