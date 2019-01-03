import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VacanteTableComponent } from './vacante-table.component';

describe('VacanteTableComponent', () => {
  let component: VacanteTableComponent;
  let fixture: ComponentFixture<VacanteTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VacanteTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VacanteTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
