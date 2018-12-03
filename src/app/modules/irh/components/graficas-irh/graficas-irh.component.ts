import { Component, OnInit } from '@angular/core';
import {EnterprisesService} from '../../services/enterprises.service';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {ProyectoService} from '../../../clb/services/proyecto.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-graficas-irh',
  templateUrl: './graficas-irh.component.html',
  styleUrls: ['./graficas-irh.component.scss']
})
export class GraficasIRHComponent implements OnInit {

  companys = [];
  company = {
    id: null
  };
  media = [];
  scores = [];
  pies = [];
  categories = [];
  mobile = false;
  createCharts = false;
  constructor(private ProyectServices: ProyectoService, private route: ActivatedRoute, private router: Router, breakpointObserver: BreakpointObserver, private EnterprisesService: EnterprisesService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
      } else {
        this.mobile = false;
      }
    });
  }

  ngOnInit() {
    this.EnterprisesService.getEnterprises().subscribe(res => {
      console.log(res);
      this.companys = res;
    });
  }


  getGraficas() {
    this.EnterprisesService.getData(this.company.id).subscribe(res => {
      console.log(res);
      let total = 0;
      for( let tema of res['temas']) {
        this.scores.push(tema['score']);
        this.media.push(res['score']);
        this.categories.push(tema['nombre']);
      }



      this.pies.push({title: {
          text: this.company['empresa']
        },

        subtitle: {
          text: 'Calificaciones'
        },

        yAxis: {
          title: {
            text: 'Calificaciónes'
          }
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle'
        },
        xAxis: {
          categories: this.categories
        },

        series: [{
          name: 'Calificaciones',
          data: this.scores
        },{
          name: 'Media',
          data: this.media
        }],

        responsive: {
          rules: [{
            condition: {
              maxWidth: 500
            },
            chartOptions: {
              legend: {
                layout: 'horizontal',
                align: 'center',
                verticalAlign: 'bottom'
              }
            }
          }]
        }
      });
      this.createCharts = true;
    });




  }
  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 4;
    }

  }

}
