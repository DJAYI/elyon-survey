import { TestBed } from '@angular/core/testing';

import { QuestionManagementApiService } from './question-management-api.service';

describe('QuestionManagementApiService', () => {
  let service: QuestionManagementApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionManagementApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
