package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveQuestionDto;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.implementations.question.QuestionManagementImpl;
import com.elyon_yireh.surveys.service.implementations.question.QuestionQueryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/surveys")
@PreAuthorize("denyAll()")
public class QuestionController {

    @Autowired
    private QuestionManagementImpl questionManagement;

    @Autowired
    private QuestionQueryImpl questionQuery;

    @PreAuthorize("permitAll()")
    @GetMapping("/{surveyId}/questions")
    public HttpResponseListDto<QuestionEntity> getQuestions(@PathVariable UUID surveyId) {
        return questionQuery.getQuestions(surveyId);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{surveyId}/questions/{questionId}")
    public HttpResponseDto<QuestionEntity> getQuestion(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId) {
        return questionQuery.getQuestion(surveyId, questionId);
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("/{surveyId}/questions")
    public HttpResponseDto<SurveyEntity> addQuestion(@Valid @PathVariable UUID surveyId, @Valid @RequestBody SaveQuestionDto saveQuestionDto) {
        return questionManagement.addQuestion(surveyId, saveQuestionDto);
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{surveyId}/questions/{questionId}")
    public HttpResponseDto<SurveyEntity> updateQuestion(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId, @Valid @RequestBody SaveQuestionDto saveQuestionDto) {
        return questionManagement.updateQuestion(surveyId, questionId, saveQuestionDto);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{surveyId}/questions/{questionId}")
    public HttpResponseDto<SurveyEntity> removeQuestion(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId) {
        return questionManagement.removeQuestion(surveyId, questionId);
    }
}
