import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaborModalAdmComponent } from './labor-modal-adm.component';

describe('LaborModalAdmComponent', () => {
  let component: LaborModalAdmComponent;
  let fixture: ComponentFixture<LaborModalAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaborModalAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaborModalAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
