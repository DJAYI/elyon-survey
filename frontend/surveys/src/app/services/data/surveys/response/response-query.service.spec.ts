import { TestBed } from '@angular/core/testing';

import { ResponseQueryService } from './response-query.service';

describe('ResponseQueryService', () => {
  let service: ResponseQueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResponseQueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
