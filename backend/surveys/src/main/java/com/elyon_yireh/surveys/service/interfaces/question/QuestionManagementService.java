package com.elyon_yireh.surveys.service.interfaces.question;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveQuestionDto;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;

import java.util.UUID;

public interface QuestionManagementService {
    public HttpResponseDto<SurveyEntity> addQuestion(UUID surveyId, SaveQuestionDto saveQuestionDto);

    public HttpResponseDto<SurveyEntity> removeQuestion(UUID surveyId, Long questionId);

    public HttpResponseDto<SurveyEntity> updateQuestion(UUID surveyId, Long questionId, SaveQuestionDto saveQuestionDto);

}
