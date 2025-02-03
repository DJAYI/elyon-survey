import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class QuestionManagementApiService {

  constructor(private http: HttpClient) { }

  saveQuestion(question: QuestionEntity, surveyId: string) {
    return this.http.post(`http://localhost:8080/api/v1/surveys/${surveyId}/questions`, question, {
      withCredentials: true
    });
  }

  deleteQuestion(questionId: number, surveyId: string) {
    return this.http.delete(`http://localhost:8080/api/v1/surveys/${surveyId}/questions/${questionId}`, {
      withCredentials: true
    });
  }
}
