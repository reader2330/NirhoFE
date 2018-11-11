import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationalDevelopmentModalIrhComponent } from './organizational-development-modal-irh.component';

describe('OrganizationalDevelopmentModalIrhComponent', () => {
  let component: OrganizationalDevelopmentModalIrhComponent;
  let fixture: ComponentFixture<OrganizationalDevelopmentModalIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizationalDevelopmentModalIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationalDevelopmentModalIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
