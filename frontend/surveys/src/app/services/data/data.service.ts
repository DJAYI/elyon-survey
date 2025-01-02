import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../model/survey';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  host = 'http://localhost:8080/api/v1';
  surveys: Survey[];
  questions: QuestionEntity[];

  constructor(private http: HttpClient) {
    this.surveys = [];
    this.questions = [];
  }

  getSurveys() {
    return this.http.get<Survey[]>(`${this.host}/surveys`, {
      responseType: 'json',
    });
  }

  getSurveyQuestions(surveyId: string) {
    return this.http.get<QuestionEntity[]>(
      `${this.host}/surveys/${surveyId}/questions`,
      {
        responseType: 'json',
      }
    );
  }
}
