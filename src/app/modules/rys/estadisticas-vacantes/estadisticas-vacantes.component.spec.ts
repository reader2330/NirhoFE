import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstadisticasVacantesComponent } from './estadisticas-vacantes.component';

describe('EstadisticasVacantesComponent', () => {
  let component: EstadisticasVacantesComponent;
  let fixture: ComponentFixture<EstadisticasVacantesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstadisticasVacantesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstadisticasVacantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
