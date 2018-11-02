import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandejaDetalleComponent } from './bandeja-detalle.component';

describe('BandejaDetalleComponent', () => {
  let component: BandejaDetalleComponent;
  let fixture: ComponentFixture<BandejaDetalleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandejaDetalleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandejaDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
