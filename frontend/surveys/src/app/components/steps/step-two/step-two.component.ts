import { Component, OnInit } from '@angular/core';
import { Survey } from '../../../model/survey';
import { DataService } from '../../../services/data/data.service';
import { ResponseService } from '../../../services/data/surveys/response/response.service';
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
    public responseService: ResponseService,
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
    this.responseService.responseSurvey.surveyId = surveyId;
  }
}
