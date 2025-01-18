package com.elyon_yireh.surveys.service.interfaces.question;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.QuestionEntity;

import java.util.UUID;

public interface QuestionQueryService {
    public HttpResponseListDto<QuestionEntity> getQuestions(UUID surveyId);

    public HttpResponseDto<QuestionEntity> getQuestion(UUID surveyId, Long questionId);
}
