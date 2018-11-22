import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseApoComponent } from './enterprise-apo.component';

describe('EnterpriseApoComponent', () => {
  let component: EnterpriseApoComponent;
  let fixture: ComponentFixture<EnterpriseApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpriseApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
