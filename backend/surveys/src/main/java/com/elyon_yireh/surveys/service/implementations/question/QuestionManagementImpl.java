package com.elyon_yireh.surveys.service.implementations.question;

import com.elyon_yireh.surveys.domain.dao.mysql.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveQuestionDto;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.question.QuestionManagementService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionManagementImpl implements QuestionManagementService {
    private SurveyDao surveyDao;

    public QuestionManagementImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    @Override
    public HttpResponseDto<SurveyEntity> addQuestion(UUID surveyId, SaveQuestionDto saveQuestionDto) {
        if (surveyId == null) {
            return new HttpResponseDto<>("Survey ID is required", "SURVEY_ID_REQUIRED", "error", null);
        }

        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        if (saveQuestionDto.statement() == null) {
            return new HttpResponseDto<>("Question statement is required", "QUESTION_STATEMENT_REQUIRED", "error", null);
        }

        QuestionEntity questionEntity = QuestionEntity.builder().statement(saveQuestionDto.statement()).build();
        surveyEntity.getQuestionEntities().add(questionEntity);

        return new HttpResponseDto<>("Question added successfully", "QUESTION_ADDED", "success", surveyDao.save(surveyEntity));
    }

    @Override
    public HttpResponseDto<SurveyEntity> updateQuestion(UUID surveyId, Long questionId, SaveQuestionDto saveQuestionDto) {
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

        questionEntity.setStatement(saveQuestionDto.statement());
        return new HttpResponseDto<>("Question updated successfully", "QUESTION_UPDATED", "success", surveyDao.save(surveyEntity));

    }

    @Override
    public HttpResponseDto<SurveyEntity> removeQuestion(UUID surveyId, Long questionId) {
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

        surveyEntity.getQuestionEntities().remove(questionEntity);
        return new HttpResponseDto<>("Question removed successfully", "QUESTION_REMOVED", "success", surveyDao.save(surveyEntity));

    }

}
