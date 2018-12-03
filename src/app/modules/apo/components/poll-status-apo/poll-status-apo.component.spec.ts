import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollStatusApoComponent } from './poll-status-apo.component';

describe('PollStatusApoComponent', () => {
  let component: PollStatusApoComponent;
  let fixture: ComponentFixture<PollStatusApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollStatusApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollStatusApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
