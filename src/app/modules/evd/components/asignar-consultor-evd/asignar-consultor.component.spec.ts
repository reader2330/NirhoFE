import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarConsultorEvdComponent } from './asignar-consultor-evd.component';

describe('AsignarConsultorEvdComponent', () => {
  let component: AsignarConsultorEvdComponent;
  let fixture: ComponentFixture<AsignarConsultorEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignarConsultorEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarConsultorEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
