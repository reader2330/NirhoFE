import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarIrhComponent } from './sidebar-irh.component';

describe('SidebarIrhComponent', () => {
  let component: SidebarIrhComponent;
  let fixture: ComponentFixture<SidebarIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
