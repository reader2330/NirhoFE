import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectorAreasComponent } from './selector-areas.component';

describe('SelectorAreasComponent', () => {
  let component: SelectorAreasComponent;
  let fixture: ComponentFixture<SelectorAreasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectorAreasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectorAreasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
