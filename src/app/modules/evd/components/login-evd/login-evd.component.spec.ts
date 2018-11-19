import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginEvdComponent } from './login-evd.component';

describe('LoginEvdComponent', () => {
  let component: LoginEvdComponent;
  let fixture: ComponentFixture<LoginEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
