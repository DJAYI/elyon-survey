import { Injectable } from '@angular/core';
import { Respondent, Response, ResponseSurvey } from '../../../../model/survey-response';
import { DataService } from '../../data.service';

@Injectable({
  providedIn: 'root'
})
export class ResponseService {

  respondent: Respondent;
  response: Map<number, number>;
  responseSurvey: ResponseSurvey;

  constructor(private dataService: DataService) {
    this.respondent = {
      firstname: '',
      lastname: '',
      documentType: '',
      documentNumber: '',
      email: '',
      phone: '',
      student: false,
    }

    this.response = new Map();

    this.responseSurvey = {
      surveyId: '',
      responses: [],
      respondent: this.respondent,
    }
  }

  setRespondent(respondent: Respondent) {
    this.respondent = respondent;
  }

  addResponse(response: Response) {
    this.response.set(response.questionId!, response.answerId!);

    this.responseSurvey.responses = Array.from(this.response).map(([questionId, answerId]) => {
      return {
        questionId: questionId,
        answerId: answerId,
      }
    });
  }

  handleSubmitResponse() {
    this.dataService.postSurveyResponse(this.responseSurvey)
  }
}
