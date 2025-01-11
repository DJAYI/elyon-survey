import { Injectable } from '@angular/core';
import { Respondent, Response, ResponseSurvey } from '../../model/response';
import { DataService } from '../data/data.service';

@Injectable({
  providedIn: 'root',
})
export class StepperService {
  respondent: Respondent;
  response: Map<number, number>;
  responseSurvey: ResponseSurvey;
  currentStep: number;

  constructor(public dataService: DataService) {
    this.currentStep = 0;

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

  handleNextStep() {
    this.currentStep++;
  }

  handlePreviousStep() {
    this.currentStep--;
  }

  handleSubmitResponse() {
    this.dataService.postSurveyResponse(this.responseSurvey).subscribe();
  }
}
