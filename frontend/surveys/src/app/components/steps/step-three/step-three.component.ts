import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { QuestionEntity } from '../../../model/survey';
import { Response } from '../../../model/survey-response';
import { DataService } from '../../../services/data/data.service';
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
    public dataService: DataService,
    public stepperService: StepperService,
    public responseService: ResponseService,
  ) {
    this.response = {
      questionId: 0,
      answerId: 0,
    }

  }

  async ngOnInit(): Promise<void> {
    this.questions = await this.dataService.getSurveyQuestions(this.surveyId());
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
