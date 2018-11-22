import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataContact360Component } from './data-contact-360.component';

describe('DataContact360Component', () => {
  let component: DataContact360Component;
  let fixture: ComponentFixture<DataContact360Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataContact360Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataContact360Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
