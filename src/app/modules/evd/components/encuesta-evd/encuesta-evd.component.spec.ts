import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestaEvdComponent } from './encuesta-evd.component';

describe('EncuestaEvdComponent', () => {
  let component: EncuestaEvdComponent;
  let fixture: ComponentFixture<EncuestaEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EncuestaEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EncuestaEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
