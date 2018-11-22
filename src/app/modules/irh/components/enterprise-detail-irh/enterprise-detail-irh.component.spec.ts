import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseDetailIrhComponent } from './enterprise-detail-irh.component';

describe('EnterpriseDetailIrhComponent', () => {
  let component: EnterpriseDetailIrhComponent;
  let fixture: ComponentFixture<EnterpriseDetailIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpriseDetailIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseDetailIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
