import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollConfigComponent } from './poll-config.component';

describe('PollConfigComponent', () => {
  let component: PollConfigComponent;
  let fixture: ComponentFixture<PollConfigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollConfigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
