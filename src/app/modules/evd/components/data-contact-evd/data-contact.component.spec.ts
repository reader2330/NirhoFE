import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataContactEvdComponent } from './data-contact-evd.component';

describe('DataContactEvdComponent', () => {
  let component: DataContactEvdComponent;
  let fixture: ComponentFixture<DataContactEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataContactEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataContactEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
