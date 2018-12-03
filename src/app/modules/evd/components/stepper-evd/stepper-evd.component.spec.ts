import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepperEvdComponent } from './stepper-evd.component';

describe('StepperEvdComponent', () => {
  let component: StepperEvdComponent;
  let fixture: ComponentFixture<StepperEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepperEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepperEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
