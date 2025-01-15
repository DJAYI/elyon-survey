package com.elyon_yireh.surveys.service.implementations.answer;

import com.elyon_yireh.surveys.domain.dao.QuestionDao;
import com.elyon_yireh.surveys.domain.dao.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.AnswerEntity;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.answer.AnswerQueryService;

import java.util.UUID;

public class AnswerQueryImpl implements AnswerQueryService {
    private QuestionDao questionDao;
    private SurveyDao surveyDao;

    @Override
    public HttpResponseListDto<AnswerEntity> getAnswers(UUID surveyId, Long questionId) {
        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyId == null) {
            return new HttpResponseListDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        if (surveyEntity == null) {
            return new HttpResponseListDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }
        QuestionEntity questionEntity = surveyEntity.getQuestionEntities()
                .stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst()
                .orElse(null);

        if (questionEntity == null) {
            return new HttpResponseListDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
        }

        return new HttpResponseListDto<>("Answers found", "ANSWERS_FOUND", "success", questionEntity.getAnswerEntities());
    }

    @Override
    public HttpResponseDto<AnswerEntity> getAnswer(UUID surveyId, Long questionId, Long answerId) {
        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

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

        return new HttpResponseDto<>("Answer found", "ANSWER_FOUND", "success", answerEntity);
    }
}
