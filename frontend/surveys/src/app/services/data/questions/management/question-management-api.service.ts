import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { QuestionEntity } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class QuestionManagementApiService {
  apiUrl = environment.API_URL;

  constructor(private http: HttpClient) { }

  saveQuestion(question: QuestionEntity, surveyId: string) {
    return this.http.post(`${this.apiUrl}surveys/${surveyId}/questions`, question, {
      withCredentials: true
    });
  }

  deleteQuestion(questionId: number, surveyId: string) {
    return this.http.delete(`${this.apiUrl}surveys/${surveyId}/questions/${questionId}`, {
      withCredentials: true
    });
  }
}
