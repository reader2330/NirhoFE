import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableClient2ModalEvdComponent } from './table-client2-modal-evd.component';

describe('TableClient2ModalEvdComponent', () => {
  let component: TableClient2ModalEvdComponent;
  let fixture: ComponentFixture<TableClient2ModalEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableClient2ModalEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableClient2ModalEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
