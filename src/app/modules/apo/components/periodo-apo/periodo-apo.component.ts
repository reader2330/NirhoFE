import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {ProyectoApoService} from '../../services/proyecto-apo.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from "sweetalert2";
import {Proyecto} from '../../models/proyecto';

@Component({
  selector: 'app-periodo-apo',
  templateUrl: './periodo-apo.component.html',
  styleUrls: ['./periodo-apo.component.scss']
})
export class PeriodoApoComponent implements OnInit {

  @Output () responseHead = new EventEmitter();
  mobile = false;
  proyects = [];
  proyecto: Proyecto;

  proyectForm = new FormGroup(
    {
      idProyecto: new FormControl(0, Validators.required),
      fechaInicio: new FormControl('', Validators.required),
      fechaTermino: new FormControl('', Validators.required),
      periodo: new FormControl(0, Validators.required),
    }
  );

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

  agregarPeriodo() {
    console.log("wweee", this.proyecto)
    this.proyecto.diasGarantia = this.proyectForm.value.periodo;
    let data = {
      fechaRegistro: this.proyectForm.value.fechaInicio,
      fechaFin: this.proyectForm.value.fechaTermino,
      proyecto: this.proyecto
    };
    //this.proyecto = this.proyectForm.value;
    console.log("proyecto: ", data)
    this.ProyectApoService.updateProyect(data).subscribe(res => {
      console.log(res);
    });
  }

}
