import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseIrhComponent } from './enterprise-irh.component';

describe('EnterpriseIrhComponent', () => {
  let component: EnterpriseIrhComponent;
  let fixture: ComponentFixture<EnterpriseIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpriseIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
