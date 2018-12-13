import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarConsultorComponent } from './asignar-consultor.component';

describe('AsignarConsultorEvdComponent', () => {
  let component: AsignarConsultorComponent;
  let fixture: ComponentFixture<AsignarConsultorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignarConsultorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarConsultorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
