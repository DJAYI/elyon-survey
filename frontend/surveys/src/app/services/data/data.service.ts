import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponseSurvey } from '../../model/response';
import { QuestionEntity, Survey } from '../../model/survey';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  host = 'http://localhost:8080/api/v1';
  surveys: Survey[] = [];
  questions: QuestionEntity[];

  constructor(private http: HttpClient) {
    this.getSurveys();
    this.questions = [];
  }

  public getSurveys() {
    this.http.get<Survey[]>(`${this.host}/surveys`, {
      responseType: 'json'
    }).subscribe({
      next: data => {
        console.log(data);
      },
      error: e => console.log("Error: " + e)
    });
  }

  public getSurveyQuestions(surveyId: string, callback: (success: boolean) => void): void {
    this.http.get<QuestionEntity[]>(
      `${this.host}/surveys/${surveyId}/questions`,
      {
        responseType: 'json'
      }
    ).subscribe({
      next: data => {
        console.log(data);
        callback(true); // Éxito
      },
      error: e => {
        console.log("Error: " + e);
        callback(false); // Error
      }
    });
  }

  public postSurveyResponse(response: ResponseSurvey) {
    return this.http.post(`${this.host}/responses`, response, {
      responseType: 'json',
    }).subscribe({
      next: data => {
        console.log(data);
      },
      error: e => console.log("Error: " + e)
    })
  }
}
