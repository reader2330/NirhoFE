import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RhAdminIrhComponent } from './rh-admin-irh.component';

describe('RhAdminIrhComponent', () => {
  let component: RhAdminIrhComponent;
  let fixture: ComponentFixture<RhAdminIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RhAdminIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RhAdminIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
