import { Component, input, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { DataService } from '../../../services/data/data.service';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
  selector: 'app-step-three',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './step-three.component.html',
  styleUrl: './step-three.component.css',
})
export class StepThreeComponent implements OnInit {
  surveyId = input('');

  constructor(
    public dataService: DataService,
    public stepperService: StepperService
  ) {}

  ngOnInit(): void {
    this.getSurveyQuestions(this.surveyId());
  }

  getSurveyQuestions(surveyId: string) {
    this.dataService.getSurveyQuestions(surveyId).subscribe({
      next: (data) => {
        this.dataService.questions = data;
      },
    });
  }

  handleSubmit() {
    this.stepperService.handleSubmitResponse();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleChooseAnswer(answerId: number, questionId: number) {
    // Check if the quuestion is already answered
    const surveyResponse = this.stepperService.response.surveyResponse;

    let isAnswered = false;
    surveyResponse.forEach((response) => {
      if (response.questionId === questionId) {
        isAnswered = true;
      }
    });

    if (isAnswered) {
      // Update the answer
      surveyResponse.forEach((response) => {
        if (response.questionId === questionId) {
          response.answerId = answerId;
        }
      });
    } else {
      // Add the answer
      surveyResponse.add({ questionId, answerId });
    }
  }
}
