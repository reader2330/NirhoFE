import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CatalogsService } from '../../../clb/services/catalogs.service';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatTableDataSource } from '@angular/material';
import {Proyecto360Service} from '../../services/proyecto360.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-assign-poll',
  templateUrl: './assign-poll.component.html',
  styleUrls: ['./assign-poll.component.scss']
})
export class AssignPollComponent implements OnInit {
  assigned = [];
  assignedSelect = [];
  dataSource = new MatTableDataSource();
  mobile = false;
  displayedColumns: string[] = ['evaluador', 'participantes'];
  proyect = [];
  evaluador = [];
  participantes = [];
  proyects = [];
  participante = {};

  constructor(breakpointObserver: BreakpointObserver, private ProyectoServices: Proyecto360Service) {
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

  assign() {
    let nuevo = true
    this.assigned.push({
      evaluador: this.evaluador,
      participante: this.participante,
    });
    let obj = {
      idParticipante: this.evaluador['participantePK']['idParticipante'],
      evaluados: []
    };
    for (let ass of this.assignedSelect) {
      if (ass.idParticipante === this.evaluador['participantePK']['idParticipante']) {
        ass.evaluados.push(this.participante['participantePK']['idParticipante']);
        nuevo = false;
      }
    }
    if (nuevo) {
      obj.evaluados.push(this.participante['participantePK']['idParticipante'])
      this.assignedSelect.push(obj);
    }
    this.dataSource.data = this.assigned;
  }

  getParticipantes() {
    this.ProyectoServices.getParticipanteByProyect(this.proyect['idProyecto']).subscribe(res => {
      console.log(res);
      this.participantes = res;
    });

  }
  getProyects() {
    this.ProyectoServices.getProyects().subscribe(res => {
      console.log(res);
      this.proyects = res;
    });
  }

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }

  saveRelacion() {
    let data = {
      idProyecto: this.proyect['idProyecto'],
      evaluadores: this.assignedSelect
    };
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la asignación',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectoServices.guardarRelacion(data).subscribe(res => {
          Swal(
            'Listo.',
            'La asignación se ha guardado correctamente',
            'success'
          );
        });
      }
      });
  }


}
