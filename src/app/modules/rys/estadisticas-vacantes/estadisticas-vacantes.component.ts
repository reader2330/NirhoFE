import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';

@Component({
  selector: 'app-estadisticas-vacantes',
  templateUrl: './estadisticas-vacantes.component.html',
  styleUrls: ['./estadisticas-vacantes.component.scss']
})
export class EstadisticasVacantesComponent implements OnInit {
  totalCandidatos;
  totalVacantes;
  totalVacantesTipo;
  pies = {};
  showDatos = false;

  constructor(private Reclutamiento: ReclutamientoService) { }

  ngOnInit() {
    this.Reclutamiento.getCandidatosTotales().subscribe(res => {
      console.log(res);
      this.totalCandidatos = res;
    });
    this.Reclutamiento.getVacantesTotales().subscribe(res => {
      console.log(res);
      this.totalVacantes = res;
    });
    this.Reclutamiento.getVacanteTipo().subscribe(res => {
      console.log(res);
       this.pies = {
        chart: {
          type: 'pie',
          options3d: {
            enabled: true,
            alpha: 45,
            beta: 0
          }},
        title: {text: 'Total de vacantes'},
        subtitle: {text: 'Por tipo'},
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
          }
        },
        series: [{
          name: '#Vacantes',
          colorByPoint: true,
          data: [['Asignadas', res['asignadas']], ['Abiertas', res['abiertas']], ['Entrevista', res['entrevista']], ['Contrato', res['contrato']]]
        }]
      };
      this.totalVacantesTipo = res;
      this.showDatos = true;
    });
  }

}
