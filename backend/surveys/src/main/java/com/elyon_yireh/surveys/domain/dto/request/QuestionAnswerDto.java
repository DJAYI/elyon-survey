package com.elyon_yireh.surveys.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"questionId", "answerId"})
public record QuestionAnswerDto(@NotNull Long questionId, @NotNull Long answerId) {

}
