import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data/data.service';
import { StepperService } from '../../services/stepper/stepper.service';
import { StepOneComponent } from '../steps/step-one/step-one.component';
import { StepThreeComponent } from '../steps/step-three/step-three.component';
import { StepTwoComponent } from '../steps/step-two/step-two.component';

@Component({
    selector: 'app-stepper',
    imports: [StepOneComponent, StepTwoComponent, StepThreeComponent],
    templateUrl: './stepper.component.html',
    styleUrl: './stepper.component.css'
})
export class StepperComponent implements OnInit {
  constructor(
    public stepperService: StepperService,
    public dataService: DataService
  ) {}

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSubmitResponse() {
    this.stepperService.handleSubmitResponse();
  }

  ngOnInit(): void {
    this.getSurveys();
  }

  getSurveys() {
    this.dataService.getSurveys().subscribe({
      next: (data) => {
        this.dataService.surveys = data;
      },

      error: (e) => {
        console.log(e);
      },
    });
  }

  getSurveyQuestions(surveyId: string) {
    this.dataService.getSurveyQuestions(surveyId).subscribe({
      next: (data) => {
        this.dataService.questions = data;
        console.log(this.dataService.questions);
      },
    });
  }
}
