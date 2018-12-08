import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantesSelectorComponent } from './participantes-selector.component';

describe('ParticipantesSelectorComponent', () => {
  let component: ParticipantesSelectorComponent;
  let fixture: ComponentFixture<ParticipantesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParticipantesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticipantesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
