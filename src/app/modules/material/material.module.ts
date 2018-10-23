import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule, MatCardModule, MatGridListModule,MatInputModule,MatFormFieldModule, MatIconModule, MatListModule} from '@angular/material';


@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule
  ],
  declarations: [],
  exports: [
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule

  ]
})
export class MaterialModule { }
