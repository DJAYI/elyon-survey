import { Injectable } from '@angular/core';
import { CreateSurveySchema } from '../../../schemas/create-survey-schema';
import { QuestionEntity } from '../../../schemas/survey';
import { QuestionManagementApiService } from '../../data/questions/management/question-management-api.service';
import { SurveyManagementService } from '../../data/surveys/management/survey-management.service';

@Injectable({
  providedIn: 'root'
})
export class AdminSurveyManagementService {
  surveyData: CreateSurveySchema;

  constructor(
    private questionManagementApiService: QuestionManagementApiService,
    private surveyManagementService: SurveyManagementService
  ) {
    this.surveyData = {
      department: '',
      questions: []
    }
  }


  addDepartment(department: string) {
    this.surveyData.department = department;
  }

  addQuestion(question: QuestionEntity) {
    this.surveyData.questions.push(question);
  }

  removeQuestion(index: number) {
    this.surveyData.questions.splice(index, 1);
  }

  saveSurvey() { 
    
  }

}
