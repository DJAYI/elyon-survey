import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { QuestionEntity } from '../../../model/survey';
import { Response } from '../../../model/survey-response';
import { SurveyQueryImplService } from '../../../services/data/surveys/query/implementation/survey-query-impl.service';
import { ResponseService } from '../../../services/data/surveys/response/response.service';
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
    public surveyQueryImpl: SurveyQueryImplService,
    public stepperService: StepperService,
    public responseService: ResponseService,
  ) {
    this.response = {
      questionId: 0,
      answerId: 0,
    }

  }

  ngOnInit() {
    this.questions = this.surveyQueryImpl.recoveredQuestions;
  }

  handleSubmit() {
    this.responseService.handleSubmitResponse();
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
