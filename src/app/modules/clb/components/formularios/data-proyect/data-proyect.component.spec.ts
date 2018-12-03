import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataProyectComponent } from './data-proyect.component';

describe('DataProyectComponent', () => {
  let component: DataProyectComponent;
  let fixture: ComponentFixture<DataProyectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataProyectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataProyectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
