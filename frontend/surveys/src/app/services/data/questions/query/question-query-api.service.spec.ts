import { TestBed } from '@angular/core/testing';

import { QuestionQueryApiService } from './question-query-api.service';

describe('QuestionQueryApiService', () => {
  let service: QuestionQueryApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionQueryApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
