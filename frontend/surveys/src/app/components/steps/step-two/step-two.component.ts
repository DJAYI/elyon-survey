import { Component, OnInit } from '@angular/core';
import { Survey } from '../../../model/survey';
import { SurveyQueryImplService } from '../../../services/data/surveys/query/implementation/survey-query-impl.service';
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
    private surveyQueryImplService: SurveyQueryImplService,
  ) {
  }

  async ngOnInit() {
    this.surveys = this.surveyQueryImplService.recoveredSurveys;
  }

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSelectSurvey(surveyId?: string) {
    this.responseService.responseSurvey.surveyId = surveyId;
    this.surveyQueryImplService.getSurveyQuestions(surveyId!);
  }
}
