import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectoEspecialidadesComponent } from './selecto-especialidades.component';

describe('SelectoEspecialidadesComponent', () => {
  let component: SelectoEspecialidadesComponent;
  let fixture: ComponentFixture<SelectoEspecialidadesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectoEspecialidadesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectoEspecialidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
