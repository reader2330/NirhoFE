import { TestBed } from '@angular/core/testing';

import { ProyectoEvdService } from './proyecto-evd.service';

describe('ProyectoEvdService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProyectoEvdService = TestBed.get(ProyectoEvdService);
    expect(service).toBeTruthy();
  });
});
