import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CatalogsService } from '../../../clb/services/catalogs.service';
import { FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-poll-status',
  templateUrl: './poll-status.component.html',
  styleUrls: ['./poll-status.component.scss']
})
export class PollStatusComponent implements OnInit {
  mobile = false;
  stages = [{
    name: 'Creado',
    completed: true
  }, {
    name: 'Configurado',
    completed: true
  }, {
    name: 'Envio',
    completed: false
  }, {
    name: 'Análisis',
    completed: false
  }, {
    name: 'Terminado',
    completed: false
  }];
  projectForm = new FormGroup({
    project: new FormControl(null),
  });
  evaluador = [];

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService) {
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

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }

  ngOnInit() {
  }

  assign(){}

  getProject() {
  }

}
