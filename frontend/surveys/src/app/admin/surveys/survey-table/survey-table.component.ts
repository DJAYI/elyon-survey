import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { DepartmentEntity, Survey } from '../../../schemas/survey';
import { SurveyManagementService } from '../../../services/data/surveys/management/survey-management.service';
import { SurveyQueryApiService } from '../../../services/data/surveys/query/survey-query-api.service';
import { NotifyService } from '../../../services/utils/notification/notify.service';

@Component({
  selector: 'app-survey-table',
  imports: [TableModule, CommonModule],
  templateUrl: './survey-table.component.html',
  styleUrl: './survey-table.component.css'
})
export class SurveyTableComponent implements OnInit {
  public surveys: Survey[];
  public departmentEntities: DepartmentEntity[];

  constructor(private surveyQuery: SurveyQueryApiService, private surveyManagementService: SurveyManagementService, private notifyService: NotifyService) {
    this.departmentEntities = [];
    this.surveys = [];
  }
  handleDeleteSurvey(surveyId: string) {
    console.log(surveyId);
    this.surveyManagementService.deleteSurvey(surveyId).subscribe({
      next: response => {
        if (response.status === 'success') {
          this.surveys = this.surveys.filter(survey => survey.id !== surveyId);
          this.notifyService.showSuccess('Survey deleted', 'Survey has been deleted successfully');
        } else {
          this.notifyService.showError('Error deleting survey', response.message);
        }
      },
      error: () => {
        this.notifyService.showError('Error deleting survey', 'An error occurred while trying to delete the survey');
      }
    });
  }
  ngOnInit(): void {
    this.surveyQuery.getSurveys().subscribe({
      next: response => {
        if (response.status === 'success') {
          this.surveys = response.data as Survey[];
          this.notifyService.showSuccess('Surveys recovered', 'Surveys have been recovered successfully');
        } else {
          this.notifyService.showError('Error recovering surveys', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Err r recovering surveys', 'An error occurred while trying to recover the surveys');
      }
    })
  }



}
