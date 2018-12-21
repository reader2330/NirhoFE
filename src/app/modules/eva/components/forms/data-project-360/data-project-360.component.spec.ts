import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataProject360Component } from './data-project-360.component';

describe('DataProjectPvcComponent', () => {
  let component: DataProject360Component;
  let fixture: ComponentFixture<DataProject360Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataProject360Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataProject360Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
