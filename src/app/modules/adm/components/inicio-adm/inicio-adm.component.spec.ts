import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioAdmComponent } from './inicio-adm.component';

describe('InicioAdmComponent', () => {
  let component: InicioAdmComponent;
  let fixture: ComponentFixture<InicioAdmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioAdmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
