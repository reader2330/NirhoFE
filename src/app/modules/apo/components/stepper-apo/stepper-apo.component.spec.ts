import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepperApoComponent } from './stepper-apo.component';

describe('StepperApoComponent', () => {
  let component: StepperApoComponent;
  let fixture: ComponentFixture<StepperApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepperApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepperApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
