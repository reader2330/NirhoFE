import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CuestionarioSelectComponent } from './cuestionario-select-irh.component';

describe('CuestionarioSelectComponent', () => {
  let component: CuestionarioSelectComponent;
  let fixture: ComponentFixture<CuestionarioSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CuestionarioSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CuestionarioSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
