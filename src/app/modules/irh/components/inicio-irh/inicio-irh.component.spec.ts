import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioIrhComponent } from './inicio-irh.component';

describe('InicioIrhComponent', () => {
  let component: InicioIrhComponent;
  let fixture: ComponentFixture<InicioIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
