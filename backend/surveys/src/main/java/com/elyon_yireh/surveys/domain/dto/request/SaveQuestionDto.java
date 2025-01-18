package com.elyon_yireh.surveys.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"statement"})
public record SaveQuestionDto(@NotBlank String statement) {
}
