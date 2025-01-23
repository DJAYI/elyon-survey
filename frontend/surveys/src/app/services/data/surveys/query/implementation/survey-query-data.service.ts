import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../../../../model/survey';
import { SurveyQueryApiService } from '../survey-query-api.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryDataService {

  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(private http: HttpClient, private surveyQueryApiService: SurveyQueryApiService) {
    this.getSurveys();
  }

  public getSurveys() {
    this.surveyQueryApiService.getSurveys().subscribe({
      next: data => {
        if (data.status === 'success') {
          this.recoveredSurveys = data.data as Survey[];
          console.log(data.message);
        } else {
          console.log(data.message);
        }
      },

      error: () => {
        console.log('Error getting surveys');
      }
    })
  }

  public getSurveyQuestions(surveyId: string) {
    this.surveyQueryApiService.getSurveyQuestions(surveyId).subscribe({
      next: data => {
        if (data.status === 'success') {
          this.recoveredQuestions = data.data as QuestionEntity[];
        } else {
          console.log(data.message);
        }
      },

      error: () => {
        console.log('Error getting questions');
      }
    })
  }
}
