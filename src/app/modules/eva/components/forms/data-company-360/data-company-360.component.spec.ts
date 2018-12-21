import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataCompany360Component } from './data-company-360.component';

describe('DataCompanyPvcComponent', () => {
  let component: DataCompany360Component;
  let fixture: ComponentFixture<DataCompany360Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataCompany360Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataCompany360Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
