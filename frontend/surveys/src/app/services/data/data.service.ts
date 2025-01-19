import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { HttpResponse } from '../../model/http-response';
import { ResponseSurvey } from '../../model/response';
import { QuestionEntity, Survey } from '../../model/survey';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  host = 'http://localhost:8080/api/v1';
  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(private http: HttpClient) {
  }

  public async getSurveys(): Promise<Survey[]> {
    const surveysFromAPI = await lastValueFrom(this.http.get<HttpResponse<Survey>>(`${this.host}/surveys`, {
      responseType: 'json'
    })).then(data => {
      if (data.status === 'success') {
        this.recoveredSurveys = data.data! as Survey[];
        return this.recoveredSurveys;
      }

      console.log(data.message);
      return null;
    })

    return surveysFromAPI as Survey[];
  }

  public getSurveyQuestions(surveyId: string): Promise<QuestionEntity[]> {
    return lastValueFrom(this.http.get<HttpResponse<QuestionEntity>>(`${this.host}/surveys/${surveyId}/questions`, {
      responseType: 'json'
    })).then(data => {
      if (data.status === 'success') {
        this.recoveredQuestions = data.data as QuestionEntity[];
        return this.recoveredQuestions;
      }

      console.log(data.message);
      return [];
    })
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
