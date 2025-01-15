package com.elyon_yireh.surveys.service.interfaces.survey;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;

import java.util.UUID;

public interface SurveyQueryService {
    public HttpResponseDto getSurvey(UUID surveyId);

    public HttpResponseListDto getSurveys();
}
