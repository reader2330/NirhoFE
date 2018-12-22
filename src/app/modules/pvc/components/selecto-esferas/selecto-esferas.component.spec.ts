import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectoEsferasComponent } from './selecto-esferas.component';

describe('SelectoEsferasComponent', () => {
  let component: SelectoEsferasComponent;
  let fixture: ComponentFixture<SelectoEsferasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectoEsferasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectoEsferasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
