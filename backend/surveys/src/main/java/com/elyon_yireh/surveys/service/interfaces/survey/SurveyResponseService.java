package com.elyon_yireh.surveys.service.interfaces.survey;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveResponseDto;
import com.elyon_yireh.surveys.domain.entities.ResponseSurveyEntity;

public interface SurveyResponseService {
    public HttpResponseDto<ResponseSurveyEntity> createResponse(SaveResponseDto saveResponseDto);

    public HttpResponseDto<ResponseSurveyEntity> getResponse(Long responseId);

    public HttpResponseListDto<ResponseSurveyEntity> getResponses();
}
