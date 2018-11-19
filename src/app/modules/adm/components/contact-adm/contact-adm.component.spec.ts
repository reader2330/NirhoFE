import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactAdmComponent } from './contact-adm.component';

describe('ContactAdmComponent', () => {
  let component: ContactAdmComponent;
  let fixture: ComponentFixture<ContactAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
