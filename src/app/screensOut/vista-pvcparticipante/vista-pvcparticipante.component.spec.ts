import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VistaPVCParticipanteComponent } from './vista-pvcparticipante.component';

describe('VistaPVCParticipanteComponent', () => {
  let component: VistaPVCParticipanteComponent;
  let fixture: ComponentFixture<VistaPVCParticipanteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VistaPVCParticipanteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VistaPVCParticipanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
