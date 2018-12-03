import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandejaListaApoComponent } from './bandeja-lista-apo.component';

describe('BandejaListaApoComponent', () => {
  let component: BandejaListaApoComponent;
  let fixture: ComponentFixture<BandejaListaApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandejaListaApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandejaListaApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
