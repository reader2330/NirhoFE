import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodoRysComponent } from './periodo-rys.component';

describe('PeriodoPvcComponent', () => {
  let component: PeriodoRysComponent;
  let fixture: ComponentFixture<PeriodoRysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeriodoRysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeriodoRysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
