package com.elyon_yireh.surveys.service.interfaces.survey;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveResponseDto;

public interface SurveyResponseService {
    public HttpResponseDto createResponse(SaveResponseDto saveResponseDto);

    public HttpResponseDto getResponse(Long responseId);

    public HttpResponseListDto getResponses();
}
