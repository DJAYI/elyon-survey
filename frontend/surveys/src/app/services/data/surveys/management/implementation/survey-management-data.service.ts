import { Injectable } from '@angular/core';
import { Survey } from '../../../../../schemas/survey';
import { SurveyManagementService } from '../survey-management.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyManagementDataService {

  constructor(private surveyManagementService: SurveyManagementService) { }

  public createSurvey(survey: Survey) {
    this.surveyManagementService.createSurvey(survey).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          console.log('Survey created successfully');
        } else {
          console.error('Failed to create survey');
        }
      },

      error: () => {
        console.error('Failed to create survey');
      }
    })
  }

  public updateSurvey(survey: Survey) {
    this.surveyManagementService.updateSurvey(survey).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          console.log('Survey updated successfully');
        } else {
          console.error('Failed to update survey');
        }
      },

      error: () => {
        console.error('Failed to update survey');
      }
    })
  }

  public deleteSurvey(surveyId: string) {
    this.surveyManagementService.deleteSurvey(surveyId).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          console.log('Survey deleted successfully');
        } else {
          console.error('Failed to delete survey');
        }
      },

      error: () => {
        console.error('Failed to delete survey');
      }
    })
  }
}
