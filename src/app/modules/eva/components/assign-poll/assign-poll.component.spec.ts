import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignPollComponent } from './assign-poll.component';

describe('AssignPollComponent', () => {
  let component: AssignPollComponent;
  let fixture: ComponentFixture<AssignPollComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignPollComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignPollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
