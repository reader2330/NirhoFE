import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CatalogsService } from '../../../clb/services/catalogs.service';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-assign-poll',
  templateUrl: './assign-poll.component.html',
  styleUrls: ['./assign-poll.component.scss']
})
export class AssignPollComponent implements OnInit {
  assigned = [];
  dataSource = new MatTableDataSource();
  mobile = false;
  displayedColumns: string[] = ['evaluador', 'participantes', 'a', 'evaluar'];
  projectForm = new FormGroup({
    evaluador: new FormControl(null),
    participante: new FormControl(null),
  });
  evaluador = [];
  participantes = [];

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

  ngOnInit() {}

  assign() {
    this.assigned.push({
      evaluador: 'kjsdhfkalsjdhfkas',
      participantes: 'kjsdhfkalsjdhfkas',
      to: 'kjsdhfkalsjdhfkas',
      evaluar: 'kjsdhfkalsjdhfkas',
    });
    this.dataSource = new MatTableDataSource(this.assigned);
  }

  checkMobileCols() {
    let value = 3;
    if (this.mobile) { value = 1; }
    return value;
  }
}
