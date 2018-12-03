import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAssignsComponent } from './view-assigns.component';

describe('ViewAssignsComponent', () => {
  let component: ViewAssignsComponent;
  let fixture: ComponentFixture<ViewAssignsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAssignsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAssignsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
