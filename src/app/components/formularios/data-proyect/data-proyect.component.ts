import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';

@Component({
  selector: 'app-data-proyect',
  templateUrl: './data-proyect.component.html',
  styleUrls: ['./data-proyect.component.scss']
})
export class DataProyectComponent implements OnInit {

  periods = [
    {
      id: 1,
      description: 'Mensual'
    },
    {
      id: 2,
      description: 'Bimestral'
    },
    {
      id: 3,
      description: 'Semestral'
    },
    {
      id: 4,
      description: 'Anual'
    },

  ];
  mobile = false;
  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService) {
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
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

}
