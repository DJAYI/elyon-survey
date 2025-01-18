package com.elyon_yireh.surveys.service.interfaces.answer;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.AnswerEntity;

import java.util.UUID;

public interface AnswerQueryService {
    public HttpResponseListDto<AnswerEntity> getAnswers(UUID surveyId, Long questionId);

    public HttpResponseDto<AnswerEntity> getAnswer(UUID surveyId, Long questionId, Long answerId);
}
