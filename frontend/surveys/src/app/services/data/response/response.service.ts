import { Injectable } from '@angular/core';
import { Respondent, Response, ResponseSurvey } from '../../../schemas/survey-response';
import { NotifyService } from '../../utils/notification/notify.service';
import { StepperService } from '../../utils/stepper/stepper.service';
import { ResponseQueryDataService } from './response-query-data.service';

@Injectable({
  providedIn: 'root'
})
export class ResponseService {

  respondent: Respondent;
  response: Map<number, number>;
  responseSurvey: ResponseSurvey;

  constructor(
    private responseQueryService: ResponseQueryDataService,
    private stepperService: StepperService,
    private notifyService: NotifyService
  ) {
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
    this.responseQueryService.sendSurveyResponse(this.responseSurvey).subscribe({
      next: data => {
        if (data.status === 'success') {
          this.notifyService.showSuccess('Response sent', 'The response has been sent successfully');
        } else {
          this.notifyService.showError('Error', 'An error occurred while trying to send the response');
        }
      },

      error: () => {
        this.notifyService.showError('Error', 'An error occurred while trying to send the response');
      },

      complete: () => {
        this.stepperService.currentStep = 0;
      }
    })
  }
}
