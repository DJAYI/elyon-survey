import { TestBed } from '@angular/core/testing';

import { SurveyQueryDataService } from './survey-query-data.service';

describe('SurveyQueryDataService', () => {
  let service: SurveyQueryDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyQueryDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
