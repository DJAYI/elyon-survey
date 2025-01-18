package com.elyon_yireh.surveys.service.interfaces.survey;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveSurveyDto;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;

import java.util.UUID;

public interface SurveyManagementService {
    public HttpResponseDto<SurveyEntity> createSurvey(SaveSurveyDto saveSurveyDto);

    public HttpResponseDto<SurveyEntity> updateSurvey(SaveSurveyDto saveSurveyDto, UUID surveyId);

    public HttpResponseDto<SurveyEntity> deleteSurvey(UUID surveyId);
}
