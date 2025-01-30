import { TestBed } from '@angular/core/testing';

import { DepartmentQueryApiService } from './department-query-api.service';

describe('DepartmentQueryApiService', () => {
  let service: DepartmentQueryApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartmentQueryApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
