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
    try {
      this.stepperService.handleSubmitResponse();
      this.toastService.addToastMessage('success', 'Respuesta enviada!', 'Respueta enviada correctamente, gracias por participar');

    } catch {
      this.toastService.addToastMessage('error', 'Error al enviar respuesta', 'Ocurrió un error al enviar la respuesta, por favor intenta de nuevo');
    }
    this.toastService.showToasts();
    this.stepperService.currentStep = 0;
  }
}
