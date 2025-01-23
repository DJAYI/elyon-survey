import { TestBed } from '@angular/core/testing';

import { SurveyManagementDataService } from './survey-management-data.service';

describe('SurveyManagementDataService', () => {
  let service: SurveyManagementDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyManagementDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
