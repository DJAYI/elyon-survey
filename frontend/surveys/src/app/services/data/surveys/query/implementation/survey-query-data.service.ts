import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../../../../model/survey';
import { NotifyService } from '../../../../utils/notification/notify.service';
import { SurveyQueryApiService } from '../survey-query-api.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryDataService {

  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(
    private http: HttpClient,
    private surveyQueryApiService: SurveyQueryApiService,
    private notifyService: NotifyService
  ) {
    this.getSurveys();
  }

  public getSurveys() {
    this.surveyQueryApiService.getSurveys().subscribe({
      next: response => {
        if (response.status === 'success') {
          this.recoveredSurveys = response.data as Survey[];
          this.notifyService.showSuccess('Surveys recovered', 'Surveys have been recovered successfully');
        } else {
          this.notifyService.showError('Error recovering surveys', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error recovering surveys', 'An error occurred while trying to recover the surveys');
      }
    })
  }

  public getSurveyQuestions(surveyId: string) {
    this.surveyQueryApiService.getSurveyQuestions(surveyId).subscribe({
      next: response => {
        if (response.status === 'success') {
          this.recoveredQuestions = response.data as QuestionEntity[];
          this.notifyService.showSuccess('Questions recovered', 'Questions have been recovered successfully');
        } else {
          this.notifyService.showError('Error recovering questions', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error recovering questions', 'An error occurred while trying to recover the questions');
      }
    })
  }
}
