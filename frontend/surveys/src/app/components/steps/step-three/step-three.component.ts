import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { QuestionEntity } from '../../../schemas/survey';
import { Response } from '../../../schemas/survey-response';
import { ResponseService } from '../../../services/data/response/response.service';
import { SurveyQueryApiService } from '../../../services/data/surveys/query/survey-query-api.service';
import { StepperService } from '../../../services/utils/stepper/stepper.service';

@Component({
  selector: 'app-step-three',
  imports: [ReactiveFormsModule],
  templateUrl: './step-three.component.html',
  styleUrl: './step-three.component.css'
})
export class StepThreeComponent implements OnInit {
  surveyId = input('');
  response: Response;

  questions?: QuestionEntity[] = [];

  constructor(
    public surveyQueryData: SurveyQueryApiService,
    public stepperService: StepperService,
    public responseService: ResponseService,
  ) {
    this.response = {
      questionId: 0,
      answerId: 0,
    }

  }

  ngOnInit() {
    this.surveyQueryData.getSurveyQuestions(this.surveyId()).subscribe((data) => {
      this.questions = data.data as QuestionEntity[];
    });
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
