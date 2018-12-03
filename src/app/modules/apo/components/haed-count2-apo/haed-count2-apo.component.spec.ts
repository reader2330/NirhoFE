import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HaedCount2ApoComponent } from './haed-count2-apo.component';

describe('HaedCount2ApoComponent', () => {
  let component: HaedCount2ApoComponent;
  let fixture: ComponentFixture<HaedCount2ApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HaedCount2ApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HaedCount2ApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
