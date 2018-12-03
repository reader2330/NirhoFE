import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {EnterprisesService} from '../../../irh/services/enterprises.service';
import {ProyectoEvdService} from '../../../evd/services/proyecto-evd.service';
import Swal from "sweetalert2";
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-proyect-apo',
  templateUrl: './proyect-apo.component.html',
  styleUrls: ['./proyect-apo.component.scss']
})
export class ProyectApoComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  proyecto = [];

  proyectForm = new FormGroup(
    {
      proyecto: new FormControl('', Validators.required),
      numEmpleados: new FormControl(0, Validators.required),
      numParticipantes: new FormControl(0, Validators.required),
      sede: new FormControl('', Validators.required),
      frecuenciaEval: new FormControl('', Validators.required),
    }
  );

  constructor( breakpointObserver: BreakpointObserver, private ProyectoApoServices: ProyectoApoService) {
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
      console.log(this.mobile);
    });
  }

  ngOnInit() {
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

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

  guardarProyecto() {
    console.log(this.proyectForm.value);
    this.proyecto = this.proyectForm.value;
    sessionStorage.setItem('project', JSON.stringify(this.proyecto));
  }

}
