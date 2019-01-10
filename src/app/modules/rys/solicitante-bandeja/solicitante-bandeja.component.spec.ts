import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitanteBandejaComponent } from './solicitante-bandeja.component';

describe('SolicitanteBandejaComponent', () => {
  let component: SolicitanteBandejaComponent;
  let fixture: ComponentFixture<SolicitanteBandejaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SolicitanteBandejaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitanteBandejaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
