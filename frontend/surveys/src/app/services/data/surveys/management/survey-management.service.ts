import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpResponse } from '../../../../model/http-response';
import { Survey } from '../../../../model/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyManagementService {

  constructor(private http: HttpClient) { }

  public createSurvey(survey: Survey) {
    return this.http.post<HttpResponse<Survey>>('http://localhost:8080/api/v1/surveys', survey);
  }

  public updateSurvey(survey: Survey) {
    return this.http.put<HttpResponse<Survey>>(`http://localhost:8080/api/v1/surveys/${survey.id}`, survey);
  }

  public deleteSurvey(surveyId: string) {
    return this.http.delete<HttpResponse<Survey>>(`http://localhost:8080/api/v1/surveys/${surveyId}`);
  }
}
