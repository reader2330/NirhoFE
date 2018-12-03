import { TestBed } from '@angular/core/testing';

import { CatalogsApoService } from './catalogs-apo.service';

describe('CatalogsApoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CatalogsApoService = TestBed.get(CatalogsApoService);
    expect(service).toBeTruthy();
  });
});
