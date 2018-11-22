import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutingSyncComponent } from './routing-sync.component';

describe('RoutingSyncComponent', () => {
  let component: RoutingSyncComponent;
  let fixture: ComponentFixture<RoutingSyncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoutingSyncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutingSyncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
