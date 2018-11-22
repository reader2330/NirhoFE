import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadCountApoComponent } from './head-count-apo.component';

describe('HeadCountApoComponent', () => {
  let component: HeadCountApoComponent;
  let fixture: ComponentFixture<HeadCountApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeadCountApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadCountApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
