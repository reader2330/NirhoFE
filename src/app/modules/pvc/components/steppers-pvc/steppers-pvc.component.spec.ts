import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SteppersPvcComponent } from './steppers-pvc.component';

describe('SteppersPvcComponent', () => {
  let component: SteppersPvcComponent;
  let fixture: ComponentFixture<SteppersPvcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SteppersPvcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SteppersPvcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
