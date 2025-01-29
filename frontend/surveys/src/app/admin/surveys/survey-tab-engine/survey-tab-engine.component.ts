import { Component } from '@angular/core';
import { Select } from 'primeng/select';
import { DepartmentEntity } from '../../../model/survey';

@Component({
  selector: 'app-survey-tab-engine',
  imports: [Select],
  templateUrl: './survey-tab-engine.component.html',
  styleUrl: './survey-tab-engine.component.css'
})
export class SurveyTabEngineComponent {
  departments: DepartmentEntity[];
  departmentNames: string[];
  department: DepartmentEntity;

  constructor() {
    this.departments = [];
    this.departmentNames = [];
    this.department = {
      id: 0,
      name: '',
      code: ''
    }
  }
}
