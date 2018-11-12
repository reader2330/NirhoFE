import { TestBed } from '@angular/core/testing';

import { CatalogsAdmService } from './catalogs-adm.service';

describe('CatalogsAdmService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CatalogsAdmService = TestBed.get(CatalogsAdmService);
    expect(service).toBeTruthy();
  });
});
