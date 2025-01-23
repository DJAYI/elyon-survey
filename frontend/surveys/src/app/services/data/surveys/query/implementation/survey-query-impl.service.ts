import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../../../../model/survey';
import { SurveyQueryService } from '../survey-query.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryImplService {

  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(private http: HttpClient, private surveyQueryService: SurveyQueryService) {
    this.getSurveys();
  }

  public getSurveys() {
    this.surveyQueryService.getSurveys().subscribe({
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
    this.surveyQueryService.getSurveyQuestions(surveyId).subscribe({
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
