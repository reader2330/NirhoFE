import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Steppers360Component } from './steppers-360.component';

describe('Steppers360Component', () => {
  let component: Steppers360Component;
  let fixture: ComponentFixture<Steppers360Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Steppers360Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Steppers360Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
