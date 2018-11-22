import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioApoComponent } from './inicio-apo.component';

describe('InicioApoComponent', () => {
  let component: InicioApoComponent;
  let fixture: ComponentFixture<InicioApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
