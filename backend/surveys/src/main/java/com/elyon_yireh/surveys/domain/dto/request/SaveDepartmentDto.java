package com.elyon_yireh.surveys.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"name", "code"})
public record SaveDepartmentDto(@NotBlank String name, @NotBlank String code) {
}
