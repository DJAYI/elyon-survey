import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponseSurvey } from '../../model/response';
import { QuestionEntity, Survey } from '../../model/survey';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  host = 'http://localhost:8080/api/v1';
  surveys: Survey[];
  questions: QuestionEntity[];

  constructor(private http: HttpClient) {
    this.surveys = [
      // {
      //   id: 'asfasdas-asfasdasda-asfsdasd-asfsdas',
      //   departmentEntity: {
      //     id: 1,
      //     code: '01',
      //     name: 'Amazonas',
      //   },
      //   questionEntities: [
      //     {
      //       id: 1,
      //       statement: '¿Cuál es tu color favorito?',
      //       answerEntities: [
      //         {
      //           id: 1,
      //           statement: 'Rojo',
      //           value: 1,
      //         },
      //         {
      //           id: 2,
      //           statement: 'Azul',
      //           value: 2,
      //         },
      //         {
      //           id: 3,
      //           statement: 'Verde',
      //           value: 3,
      //         },
      //       ]
      //     },
      //     {
      //       id: 2,
      //       statement: '¿Cuál es tu animal favorito?',
      //       answerEntities: [
      //         {
      //           id: 4,
      //           statement: 'Perro',
      //           value: 1,
      //         },
      //         {
      //           id: 5,
      //           statement: 'Gato',
      //           value: 2,
      //         },
      //         {
      //           id: 6,
      //           statement: 'Iguana',
      //           value: 3,
      //         },
      //       ]
      //     },
      //     {
      //       id: 3,
      //       statement: '¿Cuál es tu instrumento favorito?',
      //       answerEntities: [
      //         {
      //           id: 7,
      //           statement: 'Guitarra',
      //           value: 1,
      //         },
      //         {
      //           id: 8,
      //           statement: 'Flauta',
      //           value: 2,
      //         },
      //         {
      //           id: 9,
      //           statement: 'Acordeon',
      //           value: 3,
      //         },
      //       ]
      //     }
      //   ]
      // }
    ];
    this.questions = [
      //   {
      //     id: 1,
      //     statement: '¿Cuál es tu color favorito?',
      //     answerEntities: [
      //       {
      //         id: 1,
      //         statement: 'Rojo',
      //         value: 1,
      //       },
      //       {
      //         id: 2,
      //         statement: 'Azul',
      //         value: 2,
      //       },
      //       {
      //         id: 3,
      //         statement: 'Verde',
      //         value: 3,
      //       },
      //     ]
      //   },
      //   {
      //     id: 2,
      //     statement: '¿Cuál es tu animal favorito?',
      //     answerEntities: [
      //       {
      //         id: 4,
      //         statement: 'Perro',
      //         value: 1,
      //       },
      //       {
      //         id: 5,
      //         statement: 'Gato',
      //         value: 2,
      //       },
      //       {
      //         id: 6,
      //         statement: 'Iguana',
      //         value: 3,
      //       },
      //     ]
      //   },
      //   {
      //     id: 3,
      //     statement: '¿Cuál es tu instrumento favorito?',
      //     answerEntities: [
      //       {
      //         id: 7,
      //         statement: 'Guitarra',
      //         value: 1,
      //       },
      //       {
      //         id: 8,
      //         statement: 'Flauta',
      //         value: 2,
      //       },
      //       {
      //         id: 9,
      //         statement: 'Acordeon',
      //         value: 3,
      //       },
      //     ]
      //   }
    ];
  }

  public getSurveys() {
    return this.http.get<Survey[]>(`${this.host}/surveys`, {
      responseType: 'json'
    });
  }

  public getSurveyQuestions(surveyId: string): QuestionEntity[] {
    this.http.get<QuestionEntity[]>(
      `${this.host}/surveys/${surveyId}/questions`,
      {
        responseType: 'json'
      }
    ).subscribe({
      next: (data) => {
        this.questions = data;
      },
      error: (e) => {
        console.log("Error: " + e);
      }
    });


    return this.questions;
  }

  public postSurveyResponse(response: ResponseSurvey) {
    return this.http.post(`${this.host}/responses`, response, {
      responseType: 'json',
    });
  }
}
