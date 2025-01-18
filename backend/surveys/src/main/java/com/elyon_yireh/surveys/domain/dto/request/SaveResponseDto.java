package com.elyon_yireh.surveys.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"surveyId", "responses", "respondent"})
public record SaveResponseDto(@NotNull UUID surveyId, @NotNull Set<QuestionAnswerDto> responses,
                              @NotNull SaveRespondentDto respondent) {
}
