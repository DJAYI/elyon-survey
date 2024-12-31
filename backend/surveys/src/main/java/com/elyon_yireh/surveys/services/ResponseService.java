package com.elyon_yireh.surveys.services;

import com.elyon_yireh.surveys.model.entities.*;
import com.elyon_yireh.surveys.model.schemas.create.CreateRespondent;
import com.elyon_yireh.surveys.model.schemas.create.CreateResponse;
import com.elyon_yireh.surveys.model.utils.DocumentEnum;
import com.elyon_yireh.surveys.repository.mysql.RespondentRepository;
import com.elyon_yireh.surveys.repository.mysql.ResponseRepository;
import com.elyon_yireh.surveys.repository.mysql.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private RespondentRepository respondentRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public List<ResponseEntity> getAll() {
        return responseRepository.findAll();
    }

    public ResponseEntity createResponse(CreateResponse response) throws Exception {
        SurveyEntity survey = surveyRepository.findById(response.surveyId()).orElseThrow(() -> new Exception("Survey not found"));
        QuestionEntity question = survey.getQuestionEntities().stream().filter(q -> q.getId().equals(response.questionId())).findFirst().orElseThrow(() -> new Exception("Question not found"));
        AnswerEntity answer = question.getAnswerEntities().stream().filter(a -> a.getId().equals(response.answerId())).findFirst().orElseThrow(() -> new Exception("Answer not found"));

        ResponseEntity responseEntity = new ResponseEntity();

        responseEntity.getRespondentEntity().add(RespondentEntity.builder()
                .name(response.respondent().firstname())
                .apellido(response.respondent().lastname())
                .documentType(DocumentEnum.valueOf(response.respondent().documentType()))
                .documentNumber(response.respondent().documentNumber())
                .email(response.respondent().email())
                .phone(response.respondent().phone())
                .student(response.respondent().student())
                .build());
        responseEntity.getSurveyEntity().add(survey);
        responseEntity.getQuestionEntities().add(question);
        responseEntity.getAnswerEntities().add(answer);

        return responseRepository.save(responseEntity);

    }
}
