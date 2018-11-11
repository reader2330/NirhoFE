import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecruitingIrhComponent } from './recruiting-irh.component';

describe('RecruitingIrhComponent', () => {
  let component: RecruitingIrhComponent;
  let fixture: ComponentFixture<RecruitingIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecruitingIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecruitingIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
