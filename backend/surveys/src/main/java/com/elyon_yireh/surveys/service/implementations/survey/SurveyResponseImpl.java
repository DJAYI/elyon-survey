package com.elyon_yireh.surveys.service.implementations.survey;

import com.elyon_yireh.surveys.domain.dao.*;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.QuestionAnswerDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveResponseDto;
import com.elyon_yireh.surveys.domain.entities.*;
import com.elyon_yireh.surveys.service.interfaces.survey.SurveyResponseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SurveyResponseImpl implements SurveyResponseService {
    private SurveyDao surveyDao;
    private RespondentDao respondentDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private ResponseSurveyDao responseSurveyDao;

    public SurveyResponseImpl(SurveyDao surveyDao, RespondentDao respondentDao, QuestionDao questionDao, AnswerDao answerDao, ResponseSurveyDao responseSurveyDao) {
        this.surveyDao = surveyDao;
        this.respondentDao = respondentDao;
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        this.responseSurveyDao = responseSurveyDao;
    }

    @Override
    public HttpResponseDto createResponse(SaveResponseDto saveResponseDto) {
        // Obtener la encuesta por ID
        SurveyEntity surveyEntity = surveyDao.findById(saveResponseDto.surveyId())
                .orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        // Verificar que todas las preguntas tienen respuestas y que no hay duplicados
        Set<Long> questionIdsInSurvey = surveyEntity.getQuestionEntities().stream()
                .map(QuestionEntity::getId)
                .collect(Collectors.toSet());

        Set<Long> providedQuestionIds = saveResponseDto.responses().stream()
                .map(QuestionAnswerDto::questionId)
                .collect(Collectors.toSet());

        if (!questionIdsInSurvey.equals(providedQuestionIds)) {
            return new HttpResponseDto<>("Mismatch between survey questions and responses",
                    "INVALID_RESPONSES", "error", null);
        }

        // Crear y guardar el respondiente
        RespondentEntity respondent = RespondentEntity.builder()
                .name(saveResponseDto.respondent().firstname())
                .apellido(saveResponseDto.respondent().lastname())
                .email(saveResponseDto.respondent().email())
                .phone(saveResponseDto.respondent().phone())
                .documentType(saveResponseDto.respondent().documentType())
                .documentNumber(saveResponseDto.respondent().documentNumber())
                .student(saveResponseDto.respondent().isStudent())
                .build();
        respondentDao.save(respondent);

        // Crear y guardar las respuestas
        List<ResponseSurveyEntity> responseSurveyEntities = new ArrayList<>();

        for (QuestionAnswerDto questionAnswerDto : saveResponseDto.responses()) {
            QuestionEntity questionEntity = surveyEntity.getQuestionEntities().stream()
                    .filter(q -> q.getId().equals(questionAnswerDto.questionId()))
                    .findFirst()
                    .orElse(null);

            if (questionEntity == null) {
                return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
            }

            AnswerEntity answerEntity = questionEntity.getAnswerEntities().stream()
                    .filter(a -> a.getId().equals(questionAnswerDto.answerId()))
                    .findFirst()
                    .orElse(null);

            if (answerEntity == null) {
                return new HttpResponseDto<>("Answer not found", "ANSWER_NOT_FOUND", "error", null);
            }

            ResponseSurveyEntity responseSurveyEntity = ResponseSurveyEntity.builder()
                    .surveyEntity(surveyEntity)
                    .respondentEntity(respondent)
                    .questionEntity(questionEntity)
                    .answerEntity(answerEntity)
                    .build();

            responseSurveyEntities.add(responseSurveyEntity);
        }

        return new HttpResponseDto<>("Response processed", "RESPONSE_PROCESSED", "success", responseSurveyDao.saveAll(responseSurveyEntities));
    }


    @Override
    public HttpResponseDto<ResponseSurveyEntity> getResponse(Long responseId) {
        if (responseId == null) {
            return new HttpResponseDto<>("Response ID is required", "RESPONSE_ID_REQUIRED", "error", null);
        }

        ResponseSurveyEntity responseSurveyEntity = responseSurveyDao.findById(responseId).orElse(null);

        if (responseSurveyEntity == null) {
            return new HttpResponseDto<>("Response not found", "RESPONSE_NOT_FOUND", "error", null);
        }

        return new HttpResponseDto<>("Response found", "RESPONSE_FOUND", "success", responseSurveyEntity);
    }

    @Override
    public HttpResponseListDto<ResponseSurveyEntity> getResponses() {
        return new HttpResponseListDto<>("Responses retrieved", "RESPONSES_FOUND", "success",
                StreamSupport.stream(responseSurveyDao.findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }
}
