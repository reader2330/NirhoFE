import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodoApoComponent } from './periodo-apo.component';

describe('PeriodoPvcComponent', () => {
  let component: PeriodoApoComponent;
  let fixture: ComponentFixture<PeriodoApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeriodoApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeriodoApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
