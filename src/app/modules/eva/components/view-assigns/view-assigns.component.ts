import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CatalogsService } from '../../../clb/services/catalogs.service';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-view-assigns',
  templateUrl: './view-assigns.component.html',
  styleUrls: ['./view-assigns.component.scss']
})
export class ViewAssignsComponent implements OnInit {
  mobile = false;
  evaluators = [];
  displayedColumns: string[] = ['evaluado'];
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

  ngOnInit() {
    setTimeout(() => {
      this.getData();
    }, 100);
  }

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }

  getData() {
    this.evaluators = [{
      name: 'Jaquin Hernandez',
      participantes: ['Marcello', 'Luis', '2Pac', 'Esiquio']
    }, {
      name: 'Luis Aguirre',
      participantes: ['Marcello', 'Luis', '2Pac', 'Esiquio']
    }, {
      name: 'Julio Fuentes',
      participantes: ['Marcello', 'Luis', '2Pac', 'Esiquio']
    }, {
      name: 'Eder Nocelo',
      participantes: ['Marcello', 'Luis', '2Pac', 'Esiquio']
    }];
  }

}