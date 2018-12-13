import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from "sweetalert2";
import {Proyecto} from '../../../clb/models/proyecto';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';


@Component({
  selector: 'app-data-period-evd',
  templateUrl: './data-period-evd.component.html',
  styleUrls: ['./data-period.component.scss']
})
export class DataPeriodEvdComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();
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
  proyect = new Proyecto();
  constructor(breakpointObserver: BreakpointObserver, private ProyectService: ProyectoEvdService) {
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

    Swal({
      title: '',
      text: 'Seguro que quieres guardar el periodo de garantia',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.proyect.diasGarantia = this.periodForm.value.diasGarantia;
        let data = {
          fechaRegistro: this.periodForm.value.fechaRegistro,
          fechaFin: this.periodForm.value.fechaFin,
          proyecto: this.proyect
        };

        this.ProyectService.savePeriod(data).subscribe((res) => {

            Swal(
              'Listo.',
              'La información se guardo correctamente',
              'success'
            ).then(() => {
              this.responseChildren.emit({value: 1});
            });
          },
          (err) => {
            console.log(err);
            Swal(
              'Algo salio mal.',
              'No se pudo guarda la información',
              'error'
            ).then(() => {
              this.responseChildren.emit({value: 1});
            });
          });
      }
    });
  }
  cancelPeriod() {

  }

}
