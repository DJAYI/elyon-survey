export interface ResponseSurvey {
  surveyId?: string;
  responses?: Response[];
  respondent?: Respondent;
}

export interface Respondent {
  firstname?: string;
  lastname?: string;
  documentType?: string;
  documentNumber?: string;
  email?: string;
  phone?: string;
  student?: boolean;
}

export interface Response {
  questionId?: number;
  answerId?: number;
}
