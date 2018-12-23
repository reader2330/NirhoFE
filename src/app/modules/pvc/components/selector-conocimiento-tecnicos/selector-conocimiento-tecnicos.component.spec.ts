import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectorConocimientoTecnicosComponent } from './selector-conocimiento-tecnicos.component';

describe('SelectorConocimientoTecnicosComponent', () => {
  let component: SelectorConocimientoTecnicosComponent;
  let fixture: ComponentFixture<SelectorConocimientoTecnicosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectorConocimientoTecnicosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectorConocimientoTecnicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
