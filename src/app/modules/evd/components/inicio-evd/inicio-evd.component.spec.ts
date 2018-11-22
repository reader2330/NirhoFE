import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioEvdComponent } from './inicio-evd.component';

describe('InicioEvdComponent', () => {
  let component: InicioEvdComponent;
  let fixture: ComponentFixture<InicioEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
