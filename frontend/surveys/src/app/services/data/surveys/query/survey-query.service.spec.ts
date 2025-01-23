import { TestBed } from '@angular/core/testing';

import { SurveyQueryService } from './survey-query.service';

describe('SurveyQueryService', () => {
  let service: SurveyQueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyQueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
