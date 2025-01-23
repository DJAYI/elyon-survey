import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../model/survey';
import { SurveyQueryService } from './surveys/query/survey-query.service';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  host = 'http://localhost:8080/api/v1';
  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(private http: HttpClient, private surveyQueryService: SurveyQueryService) {
  }

  public getSurveys() {
    this.surveyQueryService.getSurveys().subscribe({
      next: data => {
        if (data.status === 'success') {
          this.recoveredSurveys = data.data as Survey[];
          console.log(this.recoveredSurveys);
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
    this.surveyQueryService.getSurveyQuestions(surveyId).subscribe({
      next: data => {
        if (data.status === 'success') {
          this.recoveredQuestions = data.data as QuestionEntity[];
          console.log(this.recoveredQuestions);
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
