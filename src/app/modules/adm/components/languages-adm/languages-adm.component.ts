import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {TableClient2ModalEvdComponent} from '../../../evd/components/modals/table-client2-modal-evd/table-client2-modal-evd.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../../../irh/components/reviews/training-irh/training-irh.component';
import {LanguageModalAdmComponent} from '../language-modal-adm/language-modal-adm.component';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}


const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'}
];

@Component({
  selector: 'app-languages-adm',
  templateUrl: './languages-adm.component.html',
  styleUrls: ['./languages-adm.component.scss']
})
export class LanguagesAdmComponent implements OnInit {

  displayedColumns: string[] = ['idioma', 'habilidad', 'nivel', 'delete'];
  dataSource = ELEMENT_DATA;

  contact = {
    id: 0,
    celular: 1234,
    email: '',
    nombre: '',
    puesto: '',
    telefono: 1234,
    tipoContacto: 1,
    empresa: {}
  };
  puestos = [];
  typeContact = [];
  mobile = false;
  contactForm = new FormGroup(
    {
      id: new FormControl(null),
      telefono: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      puesto: new FormControl(0, Validators.required),
      tipoContacto: new FormControl(0, Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      nombre: new FormControl('', Validators.required),
      celular: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      empresa:  new FormControl(null)
    }
  );



  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, public dialog: MatDialog) {
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

  openDialog(): void {
    const dialogRef = this.dialog.open(LanguageModalAdmComponent, {
      width: '750px',
      height: 'auto'
      //data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.animal = result;
    });
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }
  }

  ngOnInit() {
  }

}

@Component({
  selector: 'app-language-modal-adm',
  templateUrl: '../language-modal-adm/language-modal-adm.component.html'
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
