import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableClienteEvdComponent } from './table-cliente-evd.component';

describe('TableClienteEvdComponent', () => {
  let component: TableClienteEvdComponent;
  let fixture: ComponentFixture<TableClienteEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableClienteEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableClienteEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
