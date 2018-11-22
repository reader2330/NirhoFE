import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Sidebar360Component } from './sidebar-360.component';

describe('Sidebar360Component', () => {
  let component: Sidebar360Component;
  let fixture: ComponentFixture<Sidebar360Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Sidebar360Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Sidebar360Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
