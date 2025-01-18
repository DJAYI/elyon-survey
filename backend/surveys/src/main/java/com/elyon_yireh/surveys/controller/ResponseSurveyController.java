package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveResponseDto;
import com.elyon_yireh.surveys.domain.entities.ResponseSurveyEntity;
import com.elyon_yireh.surveys.service.implementations.survey.SurveyResponseImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/v1/responses")
public class ResponseSurveyController {
    @Autowired
    private SurveyResponseImpl surveyResponseImpl;

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public HttpResponseListDto<ResponseSurveyEntity> getResponses() {
        return surveyResponseImpl.getResponses();
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{responseId}")
    public HttpResponseDto<ResponseSurveyEntity> getResponse(@Valid @PathVariable Long responseId) {
        return surveyResponseImpl.getResponse(responseId);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public HttpResponseDto<Iterable<ResponseSurveyEntity>> createResponse(@Valid @RequestBody SaveResponseDto saveResponseDto) {
        return surveyResponseImpl.createResponse(saveResponseDto);
    }
}
