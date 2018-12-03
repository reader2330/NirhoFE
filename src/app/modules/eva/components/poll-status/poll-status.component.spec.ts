import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollStatusComponent } from './poll-status.component';

describe('PollStatusComponent', () => {
  let component: PollStatusComponent;
  let fixture: ComponentFixture<PollStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
