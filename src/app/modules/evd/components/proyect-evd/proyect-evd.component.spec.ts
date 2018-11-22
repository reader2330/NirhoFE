import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectEvdComponent } from './proyect-evd.component';

describe('ProyectEvdComponent', () => {
  let component: ProyectEvdComponent;
  let fixture: ComponentFixture<ProyectEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProyectEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProyectEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
