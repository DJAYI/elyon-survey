package com.elyon_yireh.surveys.service.implementations.answer;

import com.elyon_yireh.surveys.domain.dao.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveAnswerDto;
import com.elyon_yireh.surveys.domain.entities.AnswerEntity;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.answer.AnswerManagementService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerManagementImpl implements AnswerManagementService {
    private SurveyDao surveyDao;

    public AnswerManagementImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    @Override
    public HttpResponseDto<SurveyEntity> saveAnswer(UUID surveyId, Long questionId, SaveAnswerDto saveAnswerDto) {
        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        QuestionEntity questionEntity = surveyEntity.getQuestionEntities().stream().filter(question -> question.getId().equals(questionId)).findFirst().orElse(null);

        if (questionEntity == null) {
            return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
        }

        questionEntity.getAnswerEntities().add(AnswerEntity.builder()
                .statement(saveAnswerDto.statement())
                .value(saveAnswerDto.value())
                .build());

        return new HttpResponseDto<>("Answer added successfully", "ANSWER_ADDED", "success", surveyDao.save(surveyEntity));
    }

    @Override
    public HttpResponseDto<SurveyEntity> deleteAnswer(UUID surveyId, Long questionId, Long answerId) {
        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        QuestionEntity questionEntity = surveyEntity.getQuestionEntities().stream().filter(question -> question.getId().equals(questionId)).findFirst().orElse(null);

        if (questionEntity == null) {
            return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
        }

        questionEntity.getAnswerEntities().removeIf(answer -> answer.getId().equals(answerId));
        return new HttpResponseDto<>("Answer deleted successfully", "ANSWER_DELETED", "success", surveyDao.save(surveyEntity));
    }

    @Override
    public HttpResponseDto<SurveyEntity> updateAnswer(UUID surveyId, Long questionId, Long answerId, SaveAnswerDto saveAnswerDto) {
        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        QuestionEntity questionEntity = surveyEntity.getQuestionEntities().stream().filter(question -> question.getId().equals(questionId)).findFirst().orElse(null);

        if (questionEntity == null) {
            return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
        }

        AnswerEntity answerEntity = questionEntity.getAnswerEntities().stream().filter(answer -> answer.getId().equals(answerId)).findFirst().orElse(null);

        if (answerEntity == null) {
            return new HttpResponseDto<>("Answer not found", "ANSWER_NOT_FOUND", "error", null);
        }

        answerEntity.setStatement(saveAnswerDto.statement());
        answerEntity.setValue(saveAnswerDto.value());

        return new HttpResponseDto<>("Answer updated successfully", "ANSWER_UPDATED", "success", surveyDao.save(surveyEntity));
    }


}
