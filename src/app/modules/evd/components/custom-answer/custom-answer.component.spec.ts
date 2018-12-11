import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomAnswerComponent } from './custom-answer.component';

describe('CustomAnswerComponent', () => {
  let component: CustomAnswerComponent;
  let fixture: ComponentFixture<CustomAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
