import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from "sweetalert2";
import {ReclutamientoService} from '../services/reclutamiento.service';


@Component({
  selector: 'app-periodo-rys',
  templateUrl: './periodo-rys.component.html',
  styleUrls: ['./periodo-rys.component.scss']
})
export class PeriodoRysComponent implements OnInit {

  @Output () responseHead = new EventEmitter();
  mobile = false;
  proyects = [];
  vacante= {};

  periodoForm = new FormGroup(
    {
      idProyecto: new FormControl(0, Validators.required),
      fechaInicio: new FormControl('', Validators.required),
      fechaTermino: new FormControl('', Validators.required),
      diasGarantia: new FormControl(0, Validators.required),
    }
  );
  jsonFinal = {};

  constructor(breakpointObserver: BreakpointObserver, private ProyectApoService: ReclutamientoService) {
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
    this.ProyectApoService.getVacantes().subscribe((res) => {
      console.log(res);
      this.proyects = res;
    });
  }
  getProyectID() {
      for (let vac of this.proyects) {
        if (this.periodoForm.value['idProyecto'] === vac.id ){
          this.vacante = vac;
          return;
        }
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

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  agregarPeriodo() {
    this.vacante['fechaInicial'] = this.periodoForm.controls['fechaInicio']['value'];
    this.vacante['fechaFinal'] = this.periodoForm.controls['fechaTermino']['value'];
    this.vacante['periodoGarantia'] = this.periodoForm.controls['diasGarantia']['value'];

    Swal({
      title: '',
      text: 'Seguro que quieres guardar el periodo de garantia',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectApoService.savePeriod(this.vacante['id'], this.vacante).subscribe(res => {
          Swal(
            'Listo.',
            'El periodo de garantia se guardo correctamente ',
            'success'
          ).then(() => {
            this.responseHead.emit({value: 1});
          });
        });
      }
    });
  }

}
