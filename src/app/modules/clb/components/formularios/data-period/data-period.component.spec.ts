import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataPeriodComponent } from './data-period.component';

describe('DataPeriodEva360Component', () => {
  let component: DataPeriodComponent;
  let fixture: ComponentFixture<DataPeriodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataPeriodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataPeriodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
