package com.elyon_yireh.surveys.service.implementations.answer;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveAnswerDto;
import com.elyon_yireh.surveys.service.interfaces.answer.AnswerManagementService;

public class AnswerManagementImpl implements AnswerManagementService {
    @Override
    public HttpResponseDto saveAnswer(Long surveyId, Long questionId, SaveAnswerDto saveAnswerDto) {
        return null;
    }

    @Override
    public HttpResponseDto deleteAnswer(Long surveyId, Long questionId, Long answerId) {
        return null;
    }

    @Override
    public HttpResponseDto updateAnswer(Long surveyId, Long questionId, SaveAnswerDto saveAnswerDto) {
        return null;
    }


}
