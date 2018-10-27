import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule, MatCardModule, MatGridListModule, MatInputModule, MatFormFieldModule, MatIconModule, MatListModule, MatToolbarModule, MatSidenavModule, MatOptionModule, MatSelectModule, MatStepperModule, MatTableModule} from '@angular/material';


@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatSidenavModule,
    MatOptionModule,
    MatSelectModule,
    MatStepperModule,
    MatTableModule
  ],
  declarations: [],
  exports: [
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatSidenavModule,
    MatOptionModule,
    MatSelectModule,
    MatStepperModule,
    MatTableModule

  ]
})
export class MaterialModule { }
