import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
  selector: 'app-step-one',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './step-one.component.html',
  styleUrl: './step-one.component.css',
})
export class StepOneComponent {
  constructor(public sepperService: StepperService) {}

  handleNextStep() {
    this.sepperService.handleNextStep();
  }
}
