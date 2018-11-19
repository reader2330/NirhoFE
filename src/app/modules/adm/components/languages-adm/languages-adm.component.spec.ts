import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguagesAdmComponent } from './languages-adm.component';

describe('LanguagesAdmComponent', () => {
  let component: LanguagesAdmComponent;
  let fixture: ComponentFixture<LanguagesAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LanguagesAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguagesAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
