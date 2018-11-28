import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../../clb/services/catalogs.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-data-project-360',
  templateUrl: './data-project-360.component.html',
  styleUrls: ['./data-project-360.component.scss']
})
export class DataProject360Component implements OnInit {
  projectType = [];
  mobile = false;
  project = {
    id: Number,
    projectType: Object,
    participantsNumber: Number,
    organicLevels: Number,
    administrativeLevelEmployees: Number,
    operativeLevelEmployees: Number,
    totalEmployees: Number
  };
  puestos = [];

  projectForm = new FormGroup({
    id: new FormControl(null),
    projectType: new FormControl(null),
    participantsNumber: new FormControl(null, Validators.required),
    organicLevels: new FormControl(null, Validators.required),
    administrativeLevelEmployees: new FormControl(null, Validators.required),
    operativeLevelEmployees: new FormControl(null, Validators.required),
    totalEmployees: new FormControl(null, Validators.required),
  });

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
    this.getProjectTypes();
  }

  getProjectTypes() {
    this.CatalogService.getPuestos().subscribe((res) => {
      if (res) {
       return this.projectType = res;
      }
    });

  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    }
    return 3;
  }

  saveProject() {
    this.project = this.projectForm.value;
    return console.log(this.project);

    const empresa = JSON.parse(sessionStorage.getItem('company'));
    sessionStorage.setItem('project', JSON.stringify(this.project));
  }

}
