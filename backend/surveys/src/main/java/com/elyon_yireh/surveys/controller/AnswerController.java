package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveAnswerDto;
import com.elyon_yireh.surveys.domain.entities.AnswerEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.implementations.answer.AnswerManagementImpl;
import com.elyon_yireh.surveys.service.implementations.answer.AnswerQueryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/surveys")
public class AnswerController {

    @Autowired
    private AnswerManagementImpl answerManagement;

    @Autowired
    private AnswerQueryImpl answerQuery;

    @PreAuthorize("permitAll()")
    @GetMapping("/{surveyId}/questions/{questionId}/answers")
    public HttpResponseListDto<AnswerEntity> getAnswers(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId) {
        return answerQuery.getAnswers(surveyId, questionId);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{surveyId}/questions/{questionId}/answers/{answerId}")
    public HttpResponseDto<AnswerEntity> getAnswer(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId) {
        return answerQuery.getAnswer(surveyId, questionId, answerId);
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("/{surveyId}/questions/{questionId}/answers")
    public HttpResponseDto<SurveyEntity> saveAnswer(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId, @Valid @RequestBody SaveAnswerDto saveAnswerDto) {
        return answerManagement.saveAnswer(surveyId, questionId, saveAnswerDto);
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{surveyId}/questions/{questionId}/answers/{answerId}")
    public HttpResponseDto<SurveyEntity> updateAnswer(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId, @Valid @RequestBody SaveAnswerDto saveAnswerDto) {
        return answerManagement.updateAnswer(surveyId, questionId, answerId, saveAnswerDto);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{surveyId}/questions/{questionId}/answers/{answerId}")
    public HttpResponseDto<SurveyEntity> deleteAnswer(@Valid @PathVariable UUID surveyId, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId) {
        return answerManagement.deleteAnswer(surveyId, questionId, answerId);
    }
}
