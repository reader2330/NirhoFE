import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectApoComponent } from './proyect-apo.component';

describe('ProyectApoComponent', () => {
  let component: ProyectApoComponent;
  let fixture: ComponentFixture<ProyectApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProyectApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProyectApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
