import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepperAdmComponent } from './stepper-adm.component';

describe('StepperAdmComponent', () => {
  let component: StepperAdmComponent;
  let fixture: ComponentFixture<StepperAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepperAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepperAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
