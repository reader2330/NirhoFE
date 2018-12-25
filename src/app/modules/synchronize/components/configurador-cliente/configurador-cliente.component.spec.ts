import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguradorClienteComponent } from './configurador-cliente.component';

describe('ConfiguradorClienteComponent', () => {
  let component: ConfiguradorClienteComponent;
  let fixture: ComponentFixture<ConfiguradorClienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfiguradorClienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfiguradorClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
