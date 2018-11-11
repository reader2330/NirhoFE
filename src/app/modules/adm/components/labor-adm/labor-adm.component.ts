import {Component, Inject, OnInit} from '@angular/core';
import {LanguageModalAdmComponent} from '../language-modal-adm/language-modal-adm.component';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {DialogData} from '../../../irh/components/reviews/training-irh/training-irh.component';
import {LaborModalAdmComponent} from '../labor-modal-adm/labor-modal-adm.component';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  ini: number;
  ini2: number;
  ini3: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', ini: 1, ini2: 2, ini3: 3},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', ini: 1, ini2: 2, ini3: 3}
];

@Component({
  selector: 'app-labor-adm',
  templateUrl: './labor-adm.component.html',
  styleUrls: ['./labor-adm.component.scss']
})
export class LaborAdmComponent implements OnInit {

  displayedColumns: string[] = ['puesto', 'nivel', 'date_init', 'date_end', 'antiquity', 'location', 'area', 'delete'];
  dataSource = ELEMENT_DATA;

  constructor(public dialog: MatDialog) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(LaborModalAdmComponent, {
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
  selector: 'app-labor-modal-adm',
  templateUrl: '../labor-modal-adm/labor-modal-adm.component.html'
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
