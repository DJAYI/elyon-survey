package com.elyon_yireh.surveys.service.implementations.question;

import com.elyon_yireh.surveys.domain.dao.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.question.QuestionQueryService;

import java.util.List;
import java.util.UUID;

public class QuestionQueryImpl implements QuestionQueryService {
    private SurveyDao surveyDao;

    public QuestionQueryImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    @Override
    public HttpResponseListDto<QuestionEntity> getQuestions(UUID surveyId) {
        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyId == null) {
            return new HttpResponseListDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        if (surveyEntity == null) {
            return new HttpResponseListDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        List<QuestionEntity> questionEntities = List.copyOf(surveyEntity.getQuestionEntities());

        return new HttpResponseListDto<>("Questions retrieved successfully", "QUESTIONS_RETRIEVED", "success", questionEntities);
    }

    @Override
    public HttpResponseDto<QuestionEntity> getQuestion(UUID surveyId, Long questionId) {
        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }


        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        QuestionEntity questionEntity = surveyEntity.getQuestionEntities()
                .stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst()
                .orElse(null);

        if (questionEntity == null) {
            return new HttpResponseDto<>("Question not found", "QUESTION_NOT_FOUND", "error", null);
        }

        return new HttpResponseDto<>("Question retrieved successfully", "QUESTION_RETRIEVED", "success", questionEntity);
    }
}
