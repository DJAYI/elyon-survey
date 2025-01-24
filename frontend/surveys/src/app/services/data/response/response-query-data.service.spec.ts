import { TestBed } from '@angular/core/testing';

import { ResponseQueryDataService } from './response-query-data.service';

describe('ResponseQueryDataService', () => {
  let service: ResponseQueryDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResponseQueryDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
