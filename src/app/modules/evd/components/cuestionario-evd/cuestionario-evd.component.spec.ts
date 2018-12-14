import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CuestionarioEvdComponent } from './cuestionario-evd.component';

describe('CuestionarioEva360Component', () => {
  let component: CuestionarioEvdComponent;
  let fixture: ComponentFixture<CuestionarioEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CuestionarioEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CuestionarioEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
