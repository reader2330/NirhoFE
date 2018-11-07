import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingModalIrhComponent } from './training-modal-irh.component';

describe('TrainingModalIrhComponent', () => {
  let component: TrainingModalIrhComponent;
  let fixture: ComponentFixture<TrainingModalIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingModalIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingModalIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
