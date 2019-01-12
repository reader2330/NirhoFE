import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentosCandidatoComponent } from './documentos-candidato.component';

describe('DocumentosCandidatoComponent', () => {
  let component: DocumentosCandidatoComponent;
  let fixture: ComponentFixture<DocumentosCandidatoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentosCandidatoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentosCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
