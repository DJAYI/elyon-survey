import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { HttpResponse } from '../../../../schemas/http-response';
import { QuestionEntity, Survey } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryApiService {

  apiUrl = environment.API_URL;

  constructor(private http: HttpClient) { }


  public getSurveys() {
    return this.http.get<HttpResponse<Survey[]>>(`${this.apiUrl}surveys`);
  }

  public getSurveyById(id: string) {
    return this.http.get<HttpResponse<Survey>>(`${this.apiUrl}surveys/${id}`);
  }

  public getSurveyQuestions(surveyId: string) {
    return this.http.get<HttpResponse<QuestionEntity[]>>(`${this.apiUrl}surveys/${surveyId}/questions`);
  }
}
