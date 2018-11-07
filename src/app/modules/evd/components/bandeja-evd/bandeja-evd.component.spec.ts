import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandejaEvdComponent } from './bandeja-evd.component';

describe('BandejaEvdComponent', () => {
  let component: BandejaEvdComponent;
  let fixture: ComponentFixture<BandejaEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandejaEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandejaEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
