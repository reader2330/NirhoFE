import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganigramaApoComponent } from './organigrama-apo.component';

describe('OrganigramaApoComponent', () => {
  let component: OrganigramaApoComponent;
  let fixture: ComponentFixture<OrganigramaApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganigramaApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganigramaApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
