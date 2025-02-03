import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpResponse } from '../../../../schemas/http-response';
import { Survey } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyManagementService {

  constructor(private http: HttpClient) { }

  public createSurvey(departmentId: number) {
    return this.http.post<HttpResponse<Survey>>('http://localhost:8080/api/v1/surveys', departmentId, {
      withCredentials: true
    });
  }

  public updateSurvey(survey: Survey) {
    return this.http.put<HttpResponse<Survey>>(`http://localhost:8080/api/v1/surveys/${survey.id}`, survey, {
      withCredentials: true
    });
  }

  public deleteSurvey(surveyId: string) {
    return this.http.delete<HttpResponse<Survey>>(`http://localhost:8080/api/v1/surveys/${surveyId}`, {
      withCredentials: true
    });
  }
}
