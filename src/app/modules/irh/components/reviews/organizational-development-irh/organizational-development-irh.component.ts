import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../recruiting-irh/recruiting-irh.component';
import {OrganizationalDevelopmentModalIrhComponent} from '../modals/organizational-development-modal-irh/organizational-development-modal-irh.component';

export interface PeriodicElement {
  name: string;
  rfc: string;
  street: string;
  vigencia: string;
  estado: string;
  observaciones: string;
  inversion: string;
}

export interface DialogData {
  animal: string;
  name: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {name: '1', rfc: 'Hydrogen', street: 'algo', vigencia: 'no', estado: 'estado', observaciones: 'ssss', inversion: 'inversion'},
];

@Component({
  selector: 'app-organizational-development-irh',
  templateUrl: './organizational-development-irh.component.html',
  styleUrls: ['./organizational-development-irh.component.scss']
})
export class OrganizationalDevelopmentIrhComponent implements OnInit {

  displayedColumns: string[] = ['name', 'rfc', 'street', 'vigencia', 'estado', 'observaciones', 'inversion', 'edicion'];
  dataSource = ELEMENT_DATA;

  constructor(public dialog: MatDialog){}

  openDialog(): void {
    const dialogRef = this.dialog.open(OrganizationalDevelopmentModalIrhComponent, {
      width: '750px',
      height: 'auto'
      //data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.animal = result;
    });
  }

  ngOnInit() {
  }

}

@Component({
  selector: 'app-organizational-development-modal-irh',
  templateUrl: '../modals/organizational-development-modal-irh/organizational-development-modal-irh.component.html'
})
export class DialogOverviewExampleDialog {
  enterpriseForm = {}
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
