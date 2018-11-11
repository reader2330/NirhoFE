import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadCountEvdComponent } from './head-count-evd.component';

describe('HeadCountEvdComponent', () => {
  let component: HeadCountEvdComponent;
  let fixture: ComponentFixture<HeadCountEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeadCountEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadCountEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
