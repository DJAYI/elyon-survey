import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Select } from 'primeng/select';
import { DepartmentEntity } from '../../../model/survey';
import { DepartmentQueryApiService } from '../../../services/data/department/query/department-query-api.service';
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

  formGroup: FormGroup | undefined;

  constructor(public departmentQueryApiService: DepartmentQueryApiService, public notifyService: NotifyService) {
  }
  ngOnInit() {
    this.departmentQueryApiService.getDepartments().subscribe({
      next: response => {
        if (response.status === 'success') {
          this.departments = response.data as DepartmentEntity[];

          this.notifyService.showSuccess('Departments recovered', 'Departments have been recovered successfully');
        } else {
          this.notifyService.showError('Error recovering departments', response.message);
        }
      },

      error: () => {
        this.notifyService.showError('Error recovering departments', 'An error occurred while trying to recover the departments');
      }
    })

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
}
