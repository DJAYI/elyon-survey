import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpResponse } from '../../../schemas/http-response';
import { ResponseSurvey } from '../../../schemas/survey-response';

@Injectable({
  providedIn: 'root'
})
export class ResponseQueryDataService {
  apiUrl = environment.API_URL;


  constructor(private http: HttpClient) { }

  public getResponses() {
    return this.http.get<HttpResponse<ResponseSurvey[]>>(`${this.apiUrl}responses`, {
      withCredentials: true
    });
  }

  public getResponseById(id: string) {
    return this.http.get<HttpResponse<ResponseSurvey>>(`${this.apiUrl}responses/${id}`, {
      withCredentials: true
    });
  }

  public sendSurveyResponse(response: ResponseSurvey) {
    return this.http.post<HttpResponse<ResponseSurvey>>(`${this.apiUrl}responses`,
      response,
      {
        withCredentials: true
      });
  }
}
