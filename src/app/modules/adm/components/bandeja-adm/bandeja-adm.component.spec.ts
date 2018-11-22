import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandejaAdmComponent } from './bandeja-adm.component';

describe('BandejaComponent', () => {
  let component: BandejaAdmComponent;
  let fixture: ComponentFixture<BandejaAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandejaAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandejaAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
