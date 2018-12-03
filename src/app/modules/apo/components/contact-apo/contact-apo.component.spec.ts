import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactApoComponent } from './contact-apo.component';

describe('ContactApoComponent', () => {
  let component: ContactApoComponent;
  let fixture: ComponentFixture<ContactApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
