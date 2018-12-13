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

  periodoForm = new FormGroup(
    {
      idProyecto: new FormControl(0, Validators.required),
      fechaInicio: new FormControl('', Validators.required),
      fechaTermino: new FormControl('', Validators.required),
      diasGarantia: new FormControl(0, Validators.required),
    }
  );
  jsonFinal = {};

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
  getProyectID() {
    console.log(this.periodoForm.controls['idProyecto']['value']);
    this.ProyectApoService.getProyectID(this.periodoForm.controls['idProyecto']['value']).subscribe(res => {
      console.log(res);
      this.jsonFinal['proyecto'] = res;
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
    this.jsonFinal['fechaInicio'] = this.periodoForm.controls['fechaInicio']['value'];
    this.jsonFinal['fechaTermino'] = this.periodoForm.controls['fechaTermino']['value'];
    this.jsonFinal['diasGarantia'] = this.periodoForm.controls['diasGarantia']['value'];

    Swal({
      title: '',
      text: 'Seguro que quieres guarda el periodo de garantia',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectApoService.savePeriod(this.jsonFinal).subscribe(res => {
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