package com.elyon_yireh.surveys.service.interfaces.answer;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveAnswerDto;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;

import java.util.UUID;

public interface AnswerManagementService {
    public HttpResponseDto<SurveyEntity> saveAnswer(UUID surveyId, Long questionId, SaveAnswerDto saveAnswerDto);

    public HttpResponseDto<SurveyEntity> deleteAnswer(UUID surveyId, Long questionId, Long answerId);

    public HttpResponseDto<SurveyEntity> updateAnswer(UUID surveyId, Long questionId, Long answerId, SaveAnswerDto saveAnswerDto);

}
