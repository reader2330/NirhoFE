import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageModalAdmComponent } from './language-modal-adm.component';

describe('LanguageModalAdmComponent', () => {
  let component: LanguageModalAdmComponent;
  let fixture: ComponentFixture<LanguageModalAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguageModalAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageModalAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
