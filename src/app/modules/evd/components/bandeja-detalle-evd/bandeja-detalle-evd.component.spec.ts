import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandejaDetalleEvdComponent } from './bandeja-detalle-evd.component';

describe('BandejaDetalleEva360Component', () => {
  let component: BandejaDetalleEvdComponent;
  let fixture: ComponentFixture<BandejaDetalleEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandejaDetalleEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandejaDetalleEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
