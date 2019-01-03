import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatoFormComponent } from './candidato-form.component';

describe('CandidatoFormComponent', () => {
  let component: CandidatoFormComponent;
  let fixture: ComponentFixture<CandidatoFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidatoFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
