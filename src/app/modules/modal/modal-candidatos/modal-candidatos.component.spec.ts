import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCandidatosComponent } from './modal-candidatos.component';

describe('ModalCandidatosComponent', () => {
  let component: ModalCandidatosComponent;
  let fixture: ComponentFixture<ModalCandidatosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalCandidatosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalCandidatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
