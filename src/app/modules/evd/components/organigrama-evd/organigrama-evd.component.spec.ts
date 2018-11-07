import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganigramaEvdComponent } from './organigrama-evd.component';

describe('OrganigramaEvdComponent', () => {
  let component: OrganigramaEvdComponent;
  let fixture: ComponentFixture<OrganigramaEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganigramaEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganigramaEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
