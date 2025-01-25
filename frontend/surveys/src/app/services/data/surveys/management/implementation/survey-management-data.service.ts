import { Injectable } from '@angular/core';
import { Survey } from '../../../../../model/survey';
import { NotifyService } from '../../../../utils/notification/notify.service';
import { SurveyManagementService } from '../survey-management.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyManagementDataService {

  constructor(private surveyManagementService: SurveyManagementService, private notifyService: NotifyService) { }

  public createSurvey(survey: Survey) {
    this.surveyManagementService.createSurvey(survey).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          this.notifyService.showSuccess('Encuesta creada', 'La encuesta ha sido creada exitosamente');
        } else {
          this.notifyService.showError('Error al crear la encuesta', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error al crear la encuesta', 'Ocurrió un error al intentar crear la encuesta, por favor intente nuevamente');
      }
    })
  }

  public updateSurvey(survey: Survey) {
    this.surveyManagementService.updateSurvey(survey).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          this.notifyService.showSuccess('Encuesta actualizada', 'La encuesta ha sido actualizada exitosamente');
        } else {
          this.notifyService.showError('Error al actualizar la encuesta', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error al actualizar la encuesta', 'Ocurrió un error al intentar actualizar la encuesta, por favor intente nuevamente');
      }
    })
  }

  public deleteSurvey(surveyId: string) {
    this.surveyManagementService.deleteSurvey(surveyId).subscribe({
      next: (response) => {
        if (response.status === 'success') {
          this.notifyService.showSuccess('Encuesta eliminada', 'La encuesta ha sido eliminada exitosamente');
        } else {
          this.notifyService.showError('Error al eliminar la encuesta', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error al eliminar la encuesta', 'Ocurrió un error al intentar eliminar la encuesta, por favor intente nuevamente');
      }
    })
  }
}
