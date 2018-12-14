import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataPeriodEvdComponent } from './data-period-evd.component';

describe('DataPeriodEva360Component', () => {
  let component: DataPeriodEvdComponent;
  let fixture: ComponentFixture<DataPeriodEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataPeriodEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataPeriodEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
