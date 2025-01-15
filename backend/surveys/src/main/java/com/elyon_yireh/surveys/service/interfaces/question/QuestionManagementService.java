package com.elyon_yireh.surveys.service.interfaces.question;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveQuestionDto;

public interface QuestionManagementService {
    public HttpResponseDto addQuestion(Long surveyId, SaveQuestionDto saveQuestionDto);

    public HttpResponseDto removeQuestion(Long surveyId, Long questionId);

    public HttpResponseDto updateQuestion(Long surveyId, SaveQuestionDto saveQuestionDto);

}
