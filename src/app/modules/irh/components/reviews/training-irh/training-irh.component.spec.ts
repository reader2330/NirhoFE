import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingIrhComponent } from './training-irh.component';

describe('TrainingIrhComponent', () => {
  let component: TrainingIrhComponent;
  let fixture: ComponentFixture<TrainingIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
