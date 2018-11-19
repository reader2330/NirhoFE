import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecruitingModalIrhComponent } from './recruiting-modal-irh.component';

describe('RecruitingModalIrhComponent', () => {
  let component: RecruitingModalIrhComponent;
  let fixture: ComponentFixture<RecruitingModalIrhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecruitingModalIrhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecruitingModalIrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
