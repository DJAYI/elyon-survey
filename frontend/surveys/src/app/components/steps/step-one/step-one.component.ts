import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Respondent } from '../../../model/survey-response';
import { ResponseService } from '../../../services/data/response/response.service';
import { StepperService } from '../../../services/stepper/stepper.service';

@Component({
  selector: 'app-step-one',
  imports: [ReactiveFormsModule],
  templateUrl: './step-one.component.html',
  styleUrl: './step-one.component.css'
})
export class StepOneComponent {
  respondentForm: FormGroup;
  respondentData: Respondent = {
    firstname: '',
    lastname: '',
    documentType: '',
    documentNumber: '',
    email: '',
    phone: '',
    student: false,
  }

  firstname: FormControl;
  lastname: FormControl;
  documentType: FormControl;
  documentNumber: FormControl;
  email: FormControl;
  phone: FormControl;
  student: FormControl;


  constructor(public responseService: ResponseService, public stepperService: StepperService) {
    this.firstname = new FormControl(this.respondentData.firstname, [
      Validators.required,
      Validators.maxLength(50),
      Validators.pattern('^[a-zA-Z ]*$'),
    ])
    this.lastname = new FormControl(this.respondentData.lastname, [
      Validators.required,
      Validators.maxLength(50),
      Validators.pattern('^[a-zA-Z ]*$'),
    ])
    this.documentType = new FormControl(this.respondentData.documentType, [
      Validators.required
    ])
    this.documentNumber = new FormControl(this.respondentData.documentNumber, [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
      Validators.minLength(10),
      Validators.maxLength(20),
    ])
    this.email = new FormControl(this.respondentData.email, [
      Validators.required,
      Validators.email,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    ])
    this.phone = new FormControl(this.respondentData.phone, [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
      Validators.minLength(10),
      Validators.maxLength(20),
    ])
    this.student = new FormControl(this.respondentData.student, [
      Validators.required,
    ])

    this.respondentForm = new FormGroup({
      firstname: this.firstname,
      lastname: this.lastname,
      documentType: this.documentType,
      documentNumber: this.documentNumber,
      email: this.email,
      phone: this.phone,
      student: this.student
    });
  }

  handleNextStep(): void {
    if (this.respondentForm.invalid) {
      return;
    }

    this.responseService.respondent.firstname = this.firstname.value;

    Object.assign(this.responseService.respondent, this.respondentForm.value);

    this.stepperService.handleNextStep();
  }
}
