import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioPrimaryComponent } from './formulario-primary.component';

describe('FormularioPrimaryComponent', () => {
  let component: FormularioPrimaryComponent;
  let fixture: ComponentFixture<FormularioPrimaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioPrimaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioPrimaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
