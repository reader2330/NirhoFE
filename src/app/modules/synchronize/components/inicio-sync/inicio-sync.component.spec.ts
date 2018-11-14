import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioSyncComponent } from './inicio-sync.component';

describe('InicioSyncComponent', () => {
  let component: InicioSyncComponent;
  let fixture: ComponentFixture<InicioSyncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioSyncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioSyncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
