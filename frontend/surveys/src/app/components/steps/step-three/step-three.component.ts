import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Response } from '../../../model/response';
import { QuestionEntity } from '../../../model/survey';
import { DataService } from '../../../services/data/data.service';
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
    public stepperService: StepperService
  ) {
    this.response = {
      questionId: 0,
      answerId: 0,
    }

    this.questions = [];
  }

  ngOnInit(): void {
    this.questions = this.dataService.getSurveyQuestions(this.surveyId())
  }

  handleSubmit() {
    this.stepperService.handleSubmitResponse();
    console.log(this.stepperService.response);
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleChooseAnswer(answerId: number, questionId: number) {
    this.response.answerId = answerId;
    this.response.questionId = questionId;


    this.stepperService.addResponse(this.response);
  }
}
