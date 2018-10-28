import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ProyectoService} from '../../../services/proyecto.service';
import {Proyecto} from '../../../models/proyecto';

@Component({
  selector: 'app-data-period',
  templateUrl: './data-period.component.html',
  styleUrls: ['./data-period.component.scss']
})
export class DataPeriodComponent implements OnInit {
  mobile = false;
  periodForm = new FormGroup(
    {
      id: new FormControl(null),
      fechaRegistro: new FormControl(null, [Validators.required]),
      fechaFin: new FormControl(null, [Validators.required]),
      diasGarantia: new FormControl(null,[Validators.required])
    }
  );
  proyects = [];
  proyect: Proyecto;
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

  checkMobileCols() {
      if (this.mobile) {
        return 1;
      } else {
        return 3;
      }

    }

  getProyects() {
    this.ProyectService.getProyects().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }
  savePeriod() {
    console.log(this.periodForm.value);
    this.proyect.fechaRegistro = this.periodForm.value.fechaRegistro;
    this.proyect.fechaFin = this.periodForm.value.fechaFin;
    this.proyect.diasGarantia = this.periodForm.value.diasGarantia;
    console.log(this.proyect);
    this.ProyectService.savePeriod(this.proyect).subscribe((res) => {
      console.log(res);
    });
  }

}
