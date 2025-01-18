package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveSurveyDto;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.implementations.survey.SurveyManagementImpl;
import com.elyon_yireh.surveys.service.implementations.survey.SurveyQueryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/surveys")
@PreAuthorize("denyAll()")
public class SurveyController {
    @Autowired
    private SurveyManagementImpl surveyManagement;

    @Autowired
    private SurveyQueryImpl surveyQuery;

    @PreAuthorize("permitAll()")
    @GetMapping
    public HttpResponseListDto<SurveyEntity> getSurveys() {
        return surveyQuery.getSurveys();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public HttpResponseDto<SurveyEntity> getSurvey(@Valid @PathVariable UUID id) {
        return surveyQuery.getSurvey(id);
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping
    public HttpResponseDto<SurveyEntity> createSurvey(@Valid @RequestBody SaveSurveyDto surveyDao) {
        return surveyManagement.createSurvey(surveyDao);
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{id}")
    public HttpResponseDto<SurveyEntity> updateSurvey(@Valid @RequestBody SaveSurveyDto surveyDao, @Valid @PathVariable UUID id) {
        return surveyManagement.updateSurvey(surveyDao, id);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public HttpResponseDto<SurveyEntity> deleteSurvey(@Valid @PathVariable UUID id) {
        return surveyManagement.deleteSurvey(id);
    }
}
