import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ResponseService } from '../../services/data/response/response.service';
import { SurveyQueryDataService } from '../../services/data/surveys/query/implementation/survey-query-data.service';
import { StepperService } from '../../services/utils/stepper/stepper.service';
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
    public responseService: ResponseService,
    public stepperService: StepperService,
    public surveyQueryData: SurveyQueryDataService,
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
    this.responseService.handleSubmitResponse();
    this.stepperService.currentStep = 0;
  }

  isSubmitDisabled(): boolean {
    const { recoveredQuestions } = this.surveyQueryData;
    const { responses } = this.responseService.responseSurvey;

    return recoveredQuestions.length > (responses?.length || 0) && (responses?.length || 0) <= 0;
  }

}
