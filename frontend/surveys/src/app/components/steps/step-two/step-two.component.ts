import { Component, OnInit } from '@angular/core';
import { Survey } from '../../../model/survey';
import { DataService } from '../../../services/data/data.service';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
  selector: 'app-step-two',
  imports: [],
  templateUrl: './step-two.component.html',
  styleUrl: './step-two.component.css'
})
export class StepTwoComponent implements OnInit {
  surveys: Survey[] = [];

  constructor(
    public stepperService: StepperService,
    public dataService: DataService
  ) {
  }

  async ngOnInit(): Promise<void> {
    this.surveys = await this.dataService.getSurveys();
  }

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSelectSurvey(surveyId?: string) {
    this.stepperService.responseSurvey.surveyId = surveyId;
  }
}
