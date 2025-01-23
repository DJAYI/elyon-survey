import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpResponse } from '../../../../model/http-response';
import { QuestionEntity, Survey } from '../../../../model/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryApiService {

  host = 'http://localhost:8080/api/v1/surveys';

  constructor(private http: HttpClient) { }


  public getSurveys() {
    return this.http.get<HttpResponse<Survey[]>>(`${this.host}`);
  }

  public getSurveyById(id: string) {
    return this.http.get<HttpResponse<Survey>>(`${this.host}/${id}`);
  }

  public getSurveyQuestions(surveyId: string) {
    return this.http.get<HttpResponse<QuestionEntity>>(`${this.host}/${surveyId}/questions`);
  }
}
