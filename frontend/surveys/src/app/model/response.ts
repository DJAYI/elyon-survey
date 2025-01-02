export interface Response {
  respondent: Respondent;
  surveyResponse: Set<SurveyResponse>;
  surveyId: string;
}

export interface Respondent {
  firstname: string;
  lastname: string;
  documentType: string;
  documentNumber: string;
  email: string;
  phone: string;
  student: boolean;
}

export interface SurveyResponse {
  questionId: number;
  answerId: number;
}
