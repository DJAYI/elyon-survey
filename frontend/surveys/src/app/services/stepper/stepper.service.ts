import { Injectable } from '@angular/core';
import { Respondent, Response } from '../../model/response';

@Injectable({
  providedIn: 'root',
})
export class StepperService {
  respondent: Respondent;
  response: Response;

  submittedResponse: Response[];

  currentStep: number;

  constructor() {
    this.currentStep = 0;
    this.respondent = {
      firstname: '',
      lastname: '',
      documentType: '',
      documentNumber: '',
      email: '',
      phone: '',
      student: false,
    };
    this.response = {
      respondent: this.respondent,
      surveyResponse: new Set(),
      surveyId: '',
    };

    this.submittedResponse = [];
  }

  setRespondent(respondent: Respondent) {
    this.respondent = respondent;
  }

  setResponse(response: Response) {
    this.response = response;
  }

  handleNextStep() {
    this.currentStep++;
  }

  handlePreviousStep() {
    this.currentStep--;
  }

  handleSubmitResponse() {
    console.log(this.response);
  }
}
