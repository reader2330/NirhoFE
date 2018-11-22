import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {EnterprisesService} from '../../../irh/services/enterprises.service';

@Component({
  selector: 'app-proyect-apo',
  templateUrl: './proyect-apo.component.html',
  styleUrls: ['./proyect-apo.component.scss']
})
export class ProyectApoComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  mgsInit = '';
  countries = [];
  spins = [];

  proyectForm = new FormGroup(
    {
      numParticipantes: new FormControl(0, Validators.required),
      sede: new FormControl(0, Validators.required),
      frecuencia: new FormControl(0, Validators.required),
      periodoEv: new FormControl('', Validators.required),
      proyecto: new FormControl('', Validators.required)
    }
  );

  constructor( breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private EntrepiseService: EnterprisesService) {
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
      console.log(this.mobile);
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

  checkMaxColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMaxTwoColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

}
