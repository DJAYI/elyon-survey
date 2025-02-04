import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { HttpResponse } from '../../../../schemas/http-response';
import { Survey } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyManagementService {

  apiUrl = environment.API_URL;

  constructor(private http: HttpClient) { }

  public createSurvey(departmentId: number) {
    return this.http.post<HttpResponse<Survey>>(`${this.apiUrl}surveys`, departmentId, {
      withCredentials: true
    });
  }

  public updateSurvey(survey: Survey) {
    return this.http.put<HttpResponse<Survey>>(`${this.apiUrl}surveys`, survey, {
      withCredentials: true
    });
  }

  public deleteSurvey(surveyId: string) {
    return this.http.delete<HttpResponse<Survey>>(`${this.apiUrl}surveys/${surveyId}`, {
      withCredentials: true
    });
  }
}
