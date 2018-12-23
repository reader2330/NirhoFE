import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectorConocimientoHumanosComponent } from './selector-conocimiento-humanos.component';

describe('SelectorConocimientoHumanosComponent', () => {
  let component: SelectorConocimientoHumanosComponent;
  let fixture: ComponentFixture<SelectorConocimientoHumanosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectorConocimientoHumanosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectorConocimientoHumanosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
