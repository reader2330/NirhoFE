import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarApoComponent } from './sidebar-apo.component';

describe('SidebarApoComponent', () => {
  let component: SidebarApoComponent;
  let fixture: ComponentFixture<SidebarApoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarApoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarApoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
