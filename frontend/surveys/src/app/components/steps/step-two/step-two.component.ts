import { Component, OnInit } from '@angular/core';
import { Survey } from '../../../model/survey';
import { SurveyQueryDataService } from '../../../services/data/surveys/query/implementation/survey-query-data.service';
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
    private responseService: ResponseService,
    private stepperService: StepperService,
    private surveyQueryDataService: SurveyQueryDataService,
  ) {
  }

  async ngOnInit() {
    this.surveys = this.surveyQueryDataService.recoveredSurveys;
  }

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSelectSurvey(surveyId?: string) {
    this.responseService.responseSurvey.surveyId = surveyId;
    this.surveyQueryDataService.getSurveyQuestions(surveyId!);
  }
}
