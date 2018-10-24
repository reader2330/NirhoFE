import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';

@Component({
  selector: 'app-data-contact',
  templateUrl: './data-contact.component.html',
  styleUrls: ['./data-contact.component.scss']
})
export class DataContactComponent implements OnInit {

  puestos = [];
  typeContact = [];
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

  getPuestos() {
    this.CatalogService.getPuestos().subscribe((res) => {
      console.log(res);
      if (res.data) {
       this.puestos = res.data;
      }
    });

  }
  getTypeContact() {
    this.CatalogService.getPuestos().subscribe((res) => {
      console.log(res);
      if (res.data) {
        this.typeContact = res.data;
      }
    });

  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

}
