import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectorNivelComponent } from './selector-nivel.component';

describe('SelectorNivelComponent', () => {
  let component: SelectorNivelComponent;
  let fixture: ComponentFixture<SelectorNivelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectorNivelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectorNivelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
