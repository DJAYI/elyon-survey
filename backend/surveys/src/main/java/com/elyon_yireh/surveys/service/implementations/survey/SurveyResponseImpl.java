package com.elyon_yireh.surveys.service.implementations.survey;

import com.elyon_yireh.surveys.domain.dao.*;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.QuestionAnswerDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveResponseDto;
import com.elyon_yireh.surveys.domain.entities.*;
import com.elyon_yireh.surveys.service.interfaces.survey.SurveyResponseService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public HttpResponseDto<ResponseSurveyEntity> createResponse(SaveResponseDto saveResponseDto) {
        // Obtener la encuesta por ID
        SurveyEntity surveyEntity = surveyDao.findById(saveResponseDto.surveyId())
                .orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
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

        Set<Long> processedQuestionIds = new HashSet<>();

        // Crear y guardar la respuesta

        for (QuestionAnswerDto questionAnswerDto : saveResponseDto.responses()) {
            System.out.println(questionAnswerDto);
            QuestionEntity questionEntity = surveyEntity.getQuestionEntities().stream()
                    .filter(question -> question.getId().equals(questionAnswerDto.questionId()))
                    .findFirst()
                    .orElse(null);

            if (questionEntity == null) {
                return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
            }

            if (!processedQuestionIds.add(questionAnswerDto.questionId())) {
                return new HttpResponseDto<>("Duplicate question found", "DUPLICATE_QUESTION", "error", null);
            }

            AnswerEntity answerEntity = questionEntity.getAnswerEntities().stream()
                    .filter(answer -> answer.getId().equals(questionAnswerDto.answerId()))
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

            if (saveResponseDto.responses().size() != surveyEntity.getQuestionEntities().size()) {
                return new HttpResponseDto<>("Some questions were not answered", "UNANSWERED_QUESTIONS", "error", null);
            }

            return new HttpResponseDto<>("Response created", "RESPONSE_CREATED", "success", responseSurveyDao.save(responseSurveyEntity));
        }


        return new HttpResponseDto<>("Response processed", "RESPONSE_PROCESSED", "success", null);
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
