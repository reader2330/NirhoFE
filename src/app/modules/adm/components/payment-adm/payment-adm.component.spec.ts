import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentAdmComponent } from './payment-adm.component';

describe('PaymentAdmComponent', () => {
  let component: PaymentAdmComponent;
  let fixture: ComponentFixture<PaymentAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
