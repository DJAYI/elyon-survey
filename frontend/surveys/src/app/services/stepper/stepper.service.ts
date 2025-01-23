import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StepperService {
  currentStep: number;

  constructor() {
    this.currentStep = 0;
  }
  handleNextStep() {
    this.currentStep++;
  }

  handlePreviousStep() {
    this.currentStep--;
  }
}
