package com.elyon_yireh.surveys.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"statement", "value"})
public record SaveAnswerDto(@NotBlank String statement, @NotNull Integer value) {
}
