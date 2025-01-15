package com.elyon_yireh.surveys.service.implementations.question;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveQuestionDto;
import com.elyon_yireh.surveys.service.interfaces.question.QuestionManagementService;

public class QuestionManagementImpl implements QuestionManagementService {
    @Override
    public HttpResponseDto addQuestion(Long surveyId, SaveQuestionDto saveQuestionDto) {
        return null;
    }

    @Override
    public HttpResponseDto removeQuestion(Long surveyId, Long questionId) {
        return null;
    }

    @Override
    public HttpResponseDto updateQuestion(Long surveyId, SaveQuestionDto saveQuestionDto) {
        return null;
    }
}
