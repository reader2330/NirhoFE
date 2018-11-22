import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseEvdComponent } from './enterprise-evd.component';

describe('EnterpriseEvdComponent', () => {
  let component: EnterpriseEvdComponent;
  let fixture: ComponentFixture<EnterpriseEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpriseEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
