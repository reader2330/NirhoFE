import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestaModalEvdComponent } from './encuesta-modal-evd.component';

describe('EncuestaModalEvdComponent', () => {
  let component: EncuestaModalEvdComponent;
  let fixture: ComponentFixture<EncuestaModalEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EncuestaModalEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EncuestaModalEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
