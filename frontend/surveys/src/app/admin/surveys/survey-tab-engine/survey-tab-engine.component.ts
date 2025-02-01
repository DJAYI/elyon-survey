import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Select } from 'primeng/select';
import { DepartmentEntity, Survey } from '../../../model/survey';
import { DepartmentQueryApiService } from '../../../services/data/department/query/department-query-api.service';
import { SurveyQueryApiService } from '../../../services/data/surveys/query/survey-query-api.service';
import { NotifyService } from '../../../services/utils/notification/notify.service';

@Component({
  selector: 'app-survey-tab-engine',
  imports: [Select, ReactiveFormsModule],
  templateUrl: './survey-tab-engine.component.html',
  styleUrl: './survey-tab-engine.component.css'
})
export class SurveyTabEngineComponent implements OnInit {
  departments: DepartmentEntity[] = [];
  departmentSelected: string | null = null;
  surveysFounded: Survey[] = [];

  formGroup: FormGroup | undefined;

  constructor(public departmentQueryApiService: DepartmentQueryApiService, private surveyQueryApiService: SurveyQueryApiService, public notifyService: NotifyService) {

    this.formGroup = new FormGroup({
      selectedDepartment: new FormControl<string | null>(null)
    });

    this.formGroup.get('selectedDepartment')?.valueChanges.subscribe({
      next: (value: DepartmentEntity | null) => {
        this.departmentSelected = value?.name || null;
        console.log(this.departmentSelected);
      }
    });
  }

  filterDepartments(departmentsToFilter: DepartmentEntity[]) {
    this.departments = departmentsToFilter.filter((department: DepartmentEntity) => {
      return !this.surveysFounded.some((survey: Survey) => survey.departmentEntity.code === department.code);
    });
  }

  getDepartmentsWithoutSurveys(departments: DepartmentEntity[]) {
    this.surveyQueryApiService.getSurveys().subscribe({
      next: responseSurveys => {
        if (responseSurveys.status === 'success') {
          this.surveysFounded = responseSurveys.data as Survey[];
          this.filterDepartments(departments);
        }
      },
      error: () => {
        this.notifyService.showError('Error recovering surveys', 'An error occurred while trying to recover the surveys');
      }
    });
  }

  ngOnInit() {
    this.departmentQueryApiService.getDepartments().subscribe({
      next: response => {
        if (response.status === 'success') {
          this.surveysFounded = [];

          this.getDepartmentsWithoutSurveys(response.data as DepartmentEntity[]);
        } else {
          this.notifyService.showError('Error recovering departments', response.message);
        }
      },
      error: () => {
        this.notifyService.showError('Error recovering departments', 'An error occurred while trying to recover the departments');
      }
    });
  }
}
