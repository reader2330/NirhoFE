import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {DialogData} from '../../irh/components/reviews/rh-admin-irh/rh-admin-irh.component';

@Component({
  selector: 'app-imagenes-modal',
  templateUrl: './imagenes-modal.component.html',
  styleUrls: ['./imagenes-modal.component.scss']
})
export class ImagenesModalComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data) {
  }

  ngOnInit() {
  }

}
