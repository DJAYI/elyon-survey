import { TestBed } from '@angular/core/testing';

import { SurveyQueryImplService } from './survey-query-impl.service';

describe('SurveyQueryImplService', () => {
  let service: SurveyQueryImplService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyQueryImplService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
