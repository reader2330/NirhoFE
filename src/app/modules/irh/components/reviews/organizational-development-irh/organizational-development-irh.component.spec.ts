import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationalDevelopmentIrhComponent } from './organizational-development-irh.component';

describe('OrganizationalDevelopmentIrhComponent', () => {
  let component: OrganizationalDevelopmentIrhComponent;
  let fixture: ComponentFixture<OrganizationalDevelopmentIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizationalDevelopmentIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationalDevelopmentIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
