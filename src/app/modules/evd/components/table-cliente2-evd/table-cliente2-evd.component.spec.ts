import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableCliente2EvdComponent } from './table-cliente2-evd.component';

describe('TableCliente2EvdComponent', () => {
  let component: TableCliente2EvdComponent;
  let fixture: ComponentFixture<TableCliente2EvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableCliente2EvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableCliente2EvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
