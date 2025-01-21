import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DataService } from '../../services/data/data.service';
import { StepperService } from '../../services/stepper/stepper.service';
import { ToastService } from '../../services/utils/toast/toast.service';
import { StepOneComponent } from '../steps/step-one/step-one.component';
import { StepThreeComponent } from '../steps/step-three/step-three.component';
import { StepTwoComponent } from '../steps/step-two/step-two.component';

@Component({
  selector: 'app-stepper',
  imports: [StepOneComponent, StepTwoComponent, StepThreeComponent, RouterLink],
  templateUrl: './stepper.component.html',
  styleUrl: './stepper.component.css'
})
export class StepperComponent {
  router: Router = inject(Router);

  constructor(
    public stepperService: StepperService,
    public dataService: DataService,
    public toastService: ToastService,
  ) {
  }

  handleNextStep() {
    this.stepperService.handleNextStep();
  }

  handlePreviousStep() {
    this.stepperService.handlePreviousStep();
  }

  handleSubmitResponse() {
    this.stepperService.handleSubmitResponse();
    this.stepperService.currentStep = 0;
  }

  isDisabled(): boolean {
    // Obtén la longitud de las preguntas recuperadas
    const recoveredQuestionsLength = this.dataService.recoveredQuestions.length;

    // Obtén la longitud de las respuestas, o 0 si no existen
    const responsesLength = this.stepperService.responseSurvey.responses?.length || 0;

    // Validaciones individuales
    const hasMoreRecoveredQuestions = recoveredQuestionsLength > responsesLength;
    const hasNoResponses = responsesLength <= 0;

    // Combina las validaciones
    return hasMoreRecoveredQuestions && hasNoResponses;
  }

}
