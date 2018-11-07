import { TestBed } from '@angular/core/testing';

import { LoginEvdService } from './login-evd.service';

describe('LoginEvdService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoginEvdService = TestBed.get(LoginEvdService);
    expect(service).toBeTruthy();
  });
});
