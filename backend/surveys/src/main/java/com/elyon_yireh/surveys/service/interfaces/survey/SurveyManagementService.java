package com.elyon_yireh.surveys.service.interfaces.survey;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveSurveyDto;

public interface SurveyManagementService {
    public HttpResponseDto createSurvey(SaveSurveyDto saveSurveyDto);

    public HttpResponseDto updateSurvey(SaveSurveyDto saveSurveyDto);

    public HttpResponseDto deleteSurvey(Long surveyId);
}
