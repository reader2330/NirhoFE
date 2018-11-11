import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {RecruitingModalIrhComponent} from '../modals/recruiting-modal-irh/recruiting-modal-irh.component';

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
  selector: 'app-recruiting-irh',
  templateUrl: './recruiting-irh.component.html',
  styleUrls: ['./recruiting-irh.component.scss']
})
export class RecruitingIrhComponent implements OnInit {

  displayedColumns: string[] = ['name', 'rfc', 'street', 'vigencia', 'estado', 'observaciones', 'inversion', 'edicion'];
  dataSource = ELEMENT_DATA;

  constructor(public dialog: MatDialog){}

  openDialog(): void {
    const dialogRef = this.dialog.open(RecruitingModalIrhComponent, {
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
  selector: 'app-recruiting-modal-irh',
  templateUrl: '../modals/recruiting-modal-irh/recruiting-modal-irh.component.html'
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
