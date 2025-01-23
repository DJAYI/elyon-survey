import { Injectable } from '@angular/core';
import { Respondent, Response, ResponseSurvey } from '../../../../model/survey-response';
import { StepperService } from '../../../stepper/stepper.service';
import { ResponseQueryService } from './response-query.service';

@Injectable({
  providedIn: 'root'
})
export class ResponseService {

  respondent: Respondent;
  response: Map<number, number>;
  responseSurvey: ResponseSurvey;

  constructor(private responseQueryService: ResponseQueryService, private stepperService: StepperService) {
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
    this.responseQueryService.postSurveyResponse(this.responseSurvey).subscribe({
      next: data => {
        if (data.status === 'success') {
          console.log('Response sent successfully');
        } else {
          console.log('Error sending response');
        }
      },

      error: () => {
        console.log('Error en el servidor');
      },

      complete: () => {
        this.stepperService.currentStep = 0;
      }
    })
  }
}
