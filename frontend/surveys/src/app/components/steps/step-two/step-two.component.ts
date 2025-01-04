import { Component, input } from '@angular/core';
import { Survey } from '../../../model/survey';
import { DataService } from '../../../services/data/data.service';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
    selector: 'app-step-two',
    imports: [],
    templateUrl: './step-two.component.html',
    styleUrl: './step-two.component.css'
})
export class StepTwoComponent {
  surveys = input([] as Survey[]);

  constructor(
    public stepperService: StepperService,
    public dataService: DataService
  ) {}

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSelectSurvey(surveyId?: string) {
    this.stepperService.response.surveyId = surveyId ? surveyId : '';
  }
}
