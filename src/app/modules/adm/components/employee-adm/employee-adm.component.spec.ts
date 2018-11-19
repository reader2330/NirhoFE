import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeAdmComponent } from './employee-adm.component';

describe('EmployeeAdmComponent', () => {
  let component: EmployeeAdmComponent;
  let fixture: ComponentFixture<EmployeeAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
