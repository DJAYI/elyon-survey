import { QuestionEntity } from "./survey";

export interface CreateSurveySchema {
  department: string;
  questions: QuestionEntity[];
}