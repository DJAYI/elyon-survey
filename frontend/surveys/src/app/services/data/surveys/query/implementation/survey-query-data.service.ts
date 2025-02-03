import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuestionEntity, Survey } from '../../../../../schemas/survey';
import { NotifyService } from '../../../../utils/notification/notify.service';
import { SurveyQueryApiService } from '../survey-query-api.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyQueryDataService {

  recoveredSurveys: Survey[] = [];
  recoveredQuestions: QuestionEntity[] = [];

  constructor(
    private http: HttpClient,
    private surveyQueryApiService: SurveyQueryApiService,
    private notifyService: NotifyService
  ) {
    this.getSurveys();
  }

  public getSurveys() {
    this.surveyQueryApiService.getSurveys().subscribe({
      next: response => {
        if (response.status === 'success') {
          this.recoveredSurveys = response.data as Survey[];
          this.notifyService.showSuccess('Encuestas recuperadas', 'Encuestas recuperadas exitosamente');
        } else {
          this.notifyService.showError('Error recuperando las encuestas', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error recuperando las encuestas desde el servidor', 'An error occurred while trying to recover the surveys from server, please try again later');
      }
    })
  }

  public getSurveyQuestions(surveyId: string) {
    this.surveyQueryApiService.getSurveyQuestions(surveyId).subscribe({
      next: response => {
        if (response.status === 'success') {
          this.recoveredQuestions = response.data;
          this.notifyService.showSuccess('Preguntas recuperdas exitosamente', 'Las preguntas de la encuesta han sido recuperadas exitosamente');
        } else {
          this.notifyService.showError('Error recuperando las preguntas de la encuesta seleccionada', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error recuperando las encuestas desde el servidor', 'An error occurred while trying to recover the questions from server, please try again later');
      }
    })
  }
}
