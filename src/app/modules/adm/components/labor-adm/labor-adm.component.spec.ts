import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaborAdmComponent } from './labor-adm.component';

describe('LaborAdmComponent', () => {
  let component: LaborAdmComponent;
  let fixture: ComponentFixture<LaborAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaborAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaborAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
