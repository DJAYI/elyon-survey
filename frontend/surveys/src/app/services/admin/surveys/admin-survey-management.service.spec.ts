import { TestBed } from '@angular/core/testing';

import { AdminSurveyManagementService } from './admin-survey-management.service';

describe('AdminSurveyManagementService', () => {
  let service: AdminSurveyManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminSurveyManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
