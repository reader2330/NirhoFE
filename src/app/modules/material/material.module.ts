import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule, MatCardModule, MatGridListModule, MatInputModule, MatFormFieldModule, MatIconModule, MatListModule, MatToolbarModule, MatSidenavModule, MatOptionModule, MatSelectModule, MatStepperModule,MatTableModule, MatCheckboxModule, MatExpansionModule} from '@angular/material';


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
    MatExpansionModule
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
    MatExpansionModule

  ]
})
export class MaterialModule { }
