import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { TableModule } from 'primeng/table';
import { DepartmentEntity, Survey } from '../../../model/survey';
import { SurveyQueryDataService } from '../../../services/data/surveys/query/implementation/survey-query-data.service';

@Component({
  selector: 'app-survey-table',
  imports: [TableModule, CommonModule],
  templateUrl: './survey-table.component.html',
  styleUrl: './survey-table.component.css'
})
export class SurveyTableComponent {
  public surveys: Survey[];
  public departmentEntities: DepartmentEntity[];

  constructor(private surveyQuery: SurveyQueryDataService, private surveyQueryData: SurveyQueryDataService) {
    this.departmentEntities = [];
    this.surveys = [];
  }
}
