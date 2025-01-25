import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { QuestionEntity } from '../../../model/survey';
import { Response } from '../../../model/survey-response';
import { ResponseService } from '../../../services/data/response/response.service';
import { SurveyQueryDataService } from '../../../services/data/surveys/query/implementation/survey-query-data.service';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
  selector: 'app-step-three',
  imports: [ReactiveFormsModule],
  templateUrl: './step-three.component.html',
  styleUrl: './step-three.component.css'
})
export class StepThreeComponent implements OnInit {
  surveyId = input('');
  response: Response;

  questions?: QuestionEntity[];

  constructor(
    public surveyQueryData: SurveyQueryDataService,
    public stepperService: StepperService,
    public responseService: ResponseService,
  ) {
    this.response = {
      questionId: 0,
      answerId: 0,
    }

  }

  ngOnInit() {
    this.questions = this.surveyQueryData.recoveredQuestions;
  }

  handleSubmit() {
    this.responseService.handleSendResponse();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleChooseAnswer(answerId: number, questionId: number) {
    this.response.answerId = answerId;
    this.response.questionId = questionId;


    this.responseService.addResponse(this.response);
  }
}
