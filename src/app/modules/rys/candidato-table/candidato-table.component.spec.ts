import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatoTableComponent } from './candidato-table.component';

describe('CandidatoTableComponent', () => {
  let component: CandidatoTableComponent;
  let fixture: ComponentFixture<CandidatoTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidatoTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatoTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
