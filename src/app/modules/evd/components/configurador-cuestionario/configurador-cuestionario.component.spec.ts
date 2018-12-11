import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguradorCuestionarioComponent } from './configurador-cuestionario.component';

describe('ConfiguradorCuestionarioComponent', () => {
  let component: ConfiguradorCuestionarioComponent;
  let fixture: ComponentFixture<ConfiguradorCuestionarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfiguradorCuestionarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfiguradorCuestionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
