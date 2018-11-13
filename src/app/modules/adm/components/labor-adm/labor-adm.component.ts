import {Component, Inject, OnInit} from '@angular/core';
import {LanguageModalAdmComponent} from '../language-modal-adm/language-modal-adm.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../../../irh/components/reviews/training-irh/training-irh.component';
import {LaborModalAdmComponent} from '../labor-modal-adm/labor-modal-adm.component';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

export interface laboral_interface {
  puesto: string;
  nivelLaboral: number;
  fechaInicio: number;
  fechaTermino: string;
  antiguedad: string;
  localidad: string;
  area: string;
}

@Component({
  selector: 'app-labor-adm',
  templateUrl: './labor-adm.component.html',
  styleUrls: ['./labor-adm.component.scss']
})
export class LaborAdmComponent implements OnInit {

  displayedColumns: string[] = ['puesto', 'nivelLaboral', 'fechaInicio', 'fechaTermino', 'antiguedad', 'localidad', 'area', 'delete'];
  laborales: laboral_interface[] = [] ;
  dataSource = [];
  mobile = false;
  temp = '';

  laborForm = new FormGroup(
    {
      idioma: new FormControl(0, [Validators.required]),
      nivel: new FormControl(0, Validators.required),
      habilidades: new FormControl(0, Validators.required)
    }
  );

  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, public dialog: MatDialog) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
        //this.checkMobileCols();
      } else {
        this.mobile = false;
        //this.checkMobileCols();
      }
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(LaborModalAdmComponent, {
      width: '750px',
      height: 'auto'
      //data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getFieldsJob();
      console.log('The dialog was closed');
      //this.animal = result;
    });

  }

  getFieldsJob() {
    //console.log("entaaaa")
    this.temp = JSON.parse(sessionStorage.getItem('job_detail'));
    this.laborales.push(this.temp);
    this.dataSource = this.laborales;
    //console.log("temp: ", this.dataSource);
  }
  // accent
  ngOnInit() {
  }

}

  @Component({
    selector: 'app-labor-modal-adm',
    templateUrl: '../labor-modal-adm/labor-modal-adm.component.html'
  })

