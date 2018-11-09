import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarEvdComponent } from './sidebar-evd.component';

describe('SidebarEvdComponent', () => {
  let component: SidebarEvdComponent;
  let fixture: ComponentFixture<SidebarEvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarEvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarEvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
