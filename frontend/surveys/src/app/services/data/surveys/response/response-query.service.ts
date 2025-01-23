import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpResponse } from '../../../../model/http-response';
import { ResponseSurvey } from '../../../../model/survey-response';

@Injectable({
  providedIn: 'root'
})
export class ResponseQueryService {

  private host = 'http://localhost:8080/api/v1/responses';
  constructor(private http: HttpClient) { }

  public getResponses() {
    return this.http.get<HttpResponse<ResponseSurvey[]>>(this.host);
  }

  public getResponseById(id: string) {
    return this.http.get<HttpResponse<ResponseSurvey>>(`${this.host}/${id}`);
  }

  public postSurveyResponse(response: ResponseSurvey) {
    return this.http.post<HttpResponse<ResponseSurvey>>(this.host, response);
  }
}
