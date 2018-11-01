import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule, MatCardModule, MatGridListModule, MatInputModule, MatFormFieldModule, MatIconModule, MatListModule, MatToolbarModule, MatSidenavModule, MatOptionModule, MatSelectModule, MatStepperModule,MatTableModule, MatCheckboxModule, MatExpansionModule, MatRadioModule} from '@angular/material';

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
    MatRadioModule
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
    MatRadioModule
  ]
})
export class MaterialModule { }
