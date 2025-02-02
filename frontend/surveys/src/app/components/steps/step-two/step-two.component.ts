import { Component, OnInit } from '@angular/core';
import { Survey } from '../../../model/survey';
import { ResponseService } from '../../../services/data/response/response.service';
import { SurveyQueryApiService } from '../../../services/data/surveys/query/survey-query-api.service';
import { StepperService } from '../../../services/utils/stepper/stepper.service';

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
    private surveyQueryApiService: SurveyQueryApiService,
  ) {
  }

  async ngOnInit() {
    this.surveyQueryApiService.getSurveys().subscribe((data) => {
      this.surveys = data.data as Survey[];
    });
  }

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSelectSurvey(surveyId?: string) {
    this.responseService.responseSurvey.surveyId = surveyId;
    this.surveyQueryApiService.getSurveyQuestions(surveyId!);
  }
}
