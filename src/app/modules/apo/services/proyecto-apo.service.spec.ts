import { TestBed } from '@angular/core/testing';

import { ProyectoApoService } from './proyecto-apo.service';

describe('ProyectoApoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProyectoApoService = TestBed.get(ProyectoApoService);
    expect(service).toBeTruthy();
  });
});
