import { TestBed } from '@angular/core/testing';

import { SurveyQueryApiService } from './survey-query-api.service';

describe('SurveyQueryService', () => {
  let service: SurveyQueryApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyQueryApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
