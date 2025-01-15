package com.elyon_yireh.surveys.service.interfaces.answer;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveAnswerDto;

public interface AnswerManagementService {
    public HttpResponseDto saveAnswer(Long surveyId, Long questionId, SaveAnswerDto saveAnswerDto);

    public HttpResponseDto deleteAnswer(Long surveyId, Long questionId, Long answerId);

    public HttpResponseDto updateAnswer(Long surveyId, Long questionId, SaveAnswerDto saveAnswerDto);

}
