import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RhAdminModalIrhComponent } from './rh-admin-modal-irh.component';

describe('RhAdminModalIrhComponent', () => {
  let component: RhAdminModalIrhComponent;
  let fixture: ComponentFixture<RhAdminModalIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RhAdminModalIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RhAdminModalIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
