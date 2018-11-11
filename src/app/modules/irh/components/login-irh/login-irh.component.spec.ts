import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginIrhComponent } from './login-irh.component';

describe('LoginIrhComponent', () => {
  let component: LoginIrhComponent;
  let fixture: ComponentFixture<LoginIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
