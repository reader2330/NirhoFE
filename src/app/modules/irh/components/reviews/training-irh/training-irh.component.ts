import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {RhAdminModalIrhComponent} from '../modals/rh-admin-modal-irh/rh-admin-modal-irh.component';
import {TrainingModalIrhComponent} from '../modals/training-modal-irh/training-modal-irh.component';

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
  {name: '1', rfc: 'eeee', street: 'algo', vigencia: 'no', estado: 'estado', observaciones: 'ssss', inversion: 'inversion'},
];

@Component({
  selector: 'app-training-irh',
  templateUrl: './training-irh.component.html',
  styleUrls: ['./training-irh.component.scss']
})
export class TrainingIrhComponent implements OnInit {

  displayedColumns: string[] = ['name', 'rfc', 'street', 'vigencia', 'estado', 'observaciones', 'inversion', 'edicion'];
  dataSource = ELEMENT_DATA;

  constructor(public dialog: MatDialog){}

  openDialog(): void {
    const dialogRef = this.dialog.open(TrainingModalIrhComponent, {
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
  selector: 'app-training-modal-irh',
  templateUrl: '../modals/training-modal-irh/training-modal-irh.component.html'
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
