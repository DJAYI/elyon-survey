export interface Survey {
  id?: string;
  departmentEntity?: DepartmentEntity;
  questionEntities?: QuestionEntity[];
}

export interface DepartmentEntity {
  id?: number;
  code?: string;
  name?: string;
}

export interface QuestionEntity {
  id?: number;
  statement?: string;
  answerEntities?: AnswerEntity[];
}

export interface AnswerEntity {
  id?: number;
  statement?: string;
  value?: number;
}
