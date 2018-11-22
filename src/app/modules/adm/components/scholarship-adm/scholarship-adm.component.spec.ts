import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScholarshipAdmComponent } from './scholarship-adm.component';

describe('ScholarshipAdmComponent', () => {
  let component: ScholarshipAdmComponent;
  let fixture: ComponentFixture<ScholarshipAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScholarshipAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScholarshipAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
