import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatRippleModule} from '@angular/material/core';

import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatInputModule,
  MatFormFieldModule,
  MatIconModule,
  MatListModule,
  MatToolbarModule,
  MatSidenavModule,
  MatOptionModule,
  MatSelectModule,
  MatStepperModule,
  MatTableModule,
  MatCheckboxModule,
  MatExpansionModule,
  MatRadioModule,
  MatProgressSpinnerModule,
  MatSliderModule,
  MatDialogModule,
  MatNativeDateModule,
  MatDatepickerModule, MatChipsModule, MatSnackBarModule,
} from '@angular/material';

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
    MatTableModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatRadioModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatSliderModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatChipsModule,
    MatRippleModule,
    MatSnackBarModule
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
    MatTableModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatRadioModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatSliderModule,
    MatDatepickerModule,
    MatChipsModule,
    MatRippleModule,
    MatSnackBarModule
  ],
  providers: [
    MatDatepickerModule,
  ]
})
export class MaterialModule { }
